package main.improvedSort;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import main.ExternalSort;
import main.MergeSort;

public class ImprovedExternalSort extends ExternalSort {

    private final File tempOneFile = new File("temp1.txt");
    private final File tempTwoFile = new File("temp2.txt");
    private final File tempThreeFile = new File("temp3.txt");
    private final File tempFourFile = new File("temp4.txt");

    public void sortFromFile(String initialFileName, int maxArraySize) throws IOException {
        File file = new File(initialFileName);
        firstSort(file, maxArraySize);
        //в массиве файлов всегда первые два это файлы из которых читаем, вторые два - из которых пишем
        File[] files = {tempOneFile, tempTwoFile, tempThreeFile, tempFourFile};
        int currentDivider = 2;
        while (true) {
            if (isSorted(files)) {
                break;
            }
            externalSort(currentDivider, files, maxArraySize);
            currentDivider *= 2;
            files = changeTempFiles(files);
        }
        String name = file.getName();
        file.delete();
        files[0].renameTo(new File(name));
        //записываем данные из отсортированного файла в начальный файл
        deleteTempFiles();
    }

    private File[] changeTempFiles(File[] files) {
        return new File[]{files[2], files[3], files[0], files[1]};
    }

    private void externalSort(int currentDivider, File[] files, int arrayMaxSize) throws IOException {
        files[2].delete();
        files[3].delete();

        try (DataInputStream inputStreamA = new DataInputStream(new FileInputStream(files[0]));
                DataInputStream inputStreamB = new DataInputStream(new FileInputStream(files[1]));
                DataOutputStream outputStreamA = new DataOutputStream(new FileOutputStream(files[2]));
                DataOutputStream outputStreamB = new DataOutputStream(new FileOutputStream(files[3]))) {

            DataOutputStream currentOutput = outputStreamA;
            while (inputStreamA.available() > 0 && inputStreamB.available() > 0) {
                ArrayStructure structureA = new ArrayStructure(inputStreamA, currentDivider, arrayMaxSize);
                ArrayStructure structureB = new ArrayStructure(inputStreamB, currentDivider, arrayMaxSize);
                //сравниваем массивы и записываем наименьшее значение
                while (!structureA.isFinished && !structureB.isFinished) {
                    short valueToWrite = getMinShortAndUpdateStructureValue(structureA, structureB);
                    currentOutput.writeShort(valueToWrite);
                }

                //после того как один из массивов закончился - все данные из второго
                ArrayStructure notFinishedStructure = structureA.isFinished() ? structureB : structureA;
                while (!notFinishedStructure.isFinished) {
                    currentOutput.writeShort(notFinishedStructure.getAndUpdate());
                }

                //как только закончили читать с двух отрезков - меняем файл в который записываем
                currentOutput = changeStream(currentOutput, outputStreamA, outputStreamB);
            }

            //записываем непрочитанные данные в файл
            DataInputStream notFinishedStream = inputStreamA.available() > 0 ? inputStreamA : inputStreamB;
            currentOutput.write(notFinishedStream.readAllBytes());
        }
        System.out.println(files[2].getName() + " size " + files[2].length());
        System.out.println(files[3].getName() + " size " + files[3].length());
    }

    private short getMinShortAndUpdateStructureValue(ArrayStructure structureA, ArrayStructure structureB)
            throws IOException {
        if (structureA.getCurrentShortValue() < structureB.getCurrentShortValue()) {
            return structureA.getAndUpdate();
        }
        return structureB.getAndUpdate();
    }

    private void firstSort(File inputFile, int size) throws IOException {
        //удвляем старые файлы
        deleteTempFiles();
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(inputFile));
                DataOutputStream tempFileStream1 = new DataOutputStream(new FileOutputStream(tempOneFile));
                DataOutputStream tempFileStream2 = new DataOutputStream(new FileOutputStream(tempTwoFile))) {
            DataOutputStream currentStream = tempFileStream1;
            //сортируем значения по файлам
            while (inputStream.available() > 0) {
                short[] shortArray = readShortArray(inputStream, size);
                new MergeSort().sort(shortArray);
                writeShortArray(currentStream, shortArray);
                currentStream = changeStream(currentStream, tempFileStream1, tempFileStream2);
            }
        }
    }

    private short[] readShortArray(DataInputStream inputStream, int size) throws IOException {
        byte[] byteArray =
                inputStream.available() >= size ? inputStream.readNBytes(size) : inputStream.readAllBytes();
        short[] shortArray = new short[byteArray.length / 2];
        ByteBuffer.wrap(byteArray).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shortArray);
        return shortArray;
    }

    private void writeShortArray(DataOutputStream outputStream, short[] array) throws IOException {
        ByteBuffer myByteBuffer = ByteBuffer.allocate(array.length * 2);
        myByteBuffer.order(ByteOrder.BIG_ENDIAN);

        ShortBuffer myShortBuffer = myByteBuffer.asShortBuffer();
        myShortBuffer.put(array);

        outputStream.write(myByteBuffer.array());
    }

    private boolean isSorted(File[] tempFiles) {
        if (tempFiles[1].length() == 0) {
            return true;
        }
        return false;
    }

}
