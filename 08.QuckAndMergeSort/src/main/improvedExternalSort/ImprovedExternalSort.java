package main.improvedExternalSort;

import static main.improvedExternalSort.Utils.readShortArray;
import static main.improvedExternalSort.Utils.writeShortArray;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import main.ExternalSort;
import main.MergeSort;

//Как это работает
// Первый проход - вычитываем массивы указанной длинны в память, сортируем, и попеременно их записываем в разные файлы
// Далее читаем  отсортированные массивы из каждого файла и записываем в новые файлы сортируя в нужном порядкке
//итог - сначала будет длина файла 2*длинна массива, потом 4 и тд
//Когда все уложится в один файл - сортировка завершается

public class ImprovedExternalSort implements ExternalSort {

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


    private boolean isSorted(File[] tempFiles) {
        if (tempFiles[1].length() == 0) {
            return true;
        }
        return false;
    }
    private File[] changeTempFiles(File[] files) {
        return new File[]{files[2], files[3], files[0], files[1]};
    }
    protected void deleteTempFiles() {
        tempOneFile.delete();
        tempTwoFile.delete();
        tempThreeFile.delete();
        tempFourFile.delete();
    }
    public class ArrayStructure {
        private short[] currentArray;
        private DataInputStream currentStream;
        private int currentArrayIndex;
        private final int arraySize;
        private final int currentDivider;
        private int numberOfRun;
        private short currentShortValue;
        boolean isFinished;

        public ArrayStructure(DataInputStream currentStream,
                int currentDivider, int arraySize) throws IOException {
            this.currentStream = currentStream;
            this.currentArrayIndex = 0;
            this.arraySize = arraySize/2;
            this.currentDivider = currentDivider;
            this.currentArray = readShortArray(currentStream, this.arraySize);
            this.currentShortValue = this.currentArray[0];
            this.isFinished = false;
        }

        public short getAndUpdate() throws IOException {
            short tempResult = getCurrentShortValue();
            setNextShortValue();
            return tempResult;
        }

        private void setNextShortValue() throws IOException {
            if (isFinished) {
                return;
            }
            currentArrayIndex++;

            if(currentArrayIndex >= currentArray.length) {
                currentArrayIndex = 0;
                updateShortArray();
            }

            //Если обновленный массив равен нулю - дошли до конца
            if (currentArray.length == 0) {
                isFinished = true;
                return;
            }
            currentShortValue = currentArray[currentArrayIndex];
        }

        private void updateShortArray() throws IOException {
            numberOfRun++;
            if (numberOfRun == currentDivider) {
                isFinished = true;
                return;
            }
            currentArray = readShortArray(currentStream, arraySize);
        }

        public short getCurrentShortValue() {
            return currentShortValue;
        }

        public boolean isFinished() {
            return isFinished;
        }

    }
}

