package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalSort {

    protected final File tempOneFile = new File("temp1.txt");
    protected final File tempTwoFile = new File("temp2.txt");

    public void sortFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        while (true) {
            externalSort(file);
            if (isSorted()) {
                break;
            }
            mergeTempFiles(file);
        }
        deleteTempFiles();
    }

    protected void externalSort(File inputFile) throws IOException {
        //удвляем старые файлы
        deleteTempFiles();
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(inputFile));
                DataOutputStream tempFileStream1 = new DataOutputStream(new FileOutputStream(tempOneFile));
                DataOutputStream tempFileStream2 = new DataOutputStream(new FileOutputStream(tempTwoFile))) {
            DataOutputStream currentStream = tempFileStream1;
            //записываем первое значение в первый файл
            short previousValue = inputStream.readShort();
            currentStream.writeShort(previousValue);
            //сортируем остальные значения по файлам
            while (inputStream.available() > 0) {
                short currentValue = inputStream.readShort();
                if (currentValue < previousValue) {
                    currentStream = changeStream(currentStream, tempFileStream1, tempFileStream2);
                }
                currentStream.writeShort(currentValue);
                previousValue = currentValue;
            }
        }
    }

    protected void deleteTempFiles() {
        tempOneFile.delete();
        tempTwoFile.delete();
    }

    protected boolean isSorted() {
        return tempTwoFile.length() == 0;
    }

    protected <T> T changeStream(T currentStream, T tempFileStream1,
            T tempFileStream2) {
        if (currentStream == tempFileStream1) {
            return tempFileStream2;
        } else {
            return tempFileStream1;
        }
    }

    protected void mergeTempFiles(File inputFile) throws IOException {
        inputFile.delete();
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(inputFile));
                DataInputStream tempFileStream1 = new DataInputStream(new FileInputStream(tempOneFile));
                DataInputStream tempFileStream2 = new DataInputStream(new FileInputStream(tempTwoFile))) {
            //берем еачальные значения
            DataInputStream currentInputStream = tempFileStream1;
            short lastFromOtherFile = tempFileStream2.readShort();
            short lastSavedValue = Short.MIN_VALUE;

            while (currentInputStream.available() > 0) {
                short currentValue = currentInputStream.readShort();
                //условия когда используем значения из другого файла, не из того, который только что читали
                if (((lastFromOtherFile > lastSavedValue && lastSavedValue > currentValue) ||
                        (lastFromOtherFile < currentValue && currentValue < lastSavedValue) ||
                        (lastFromOtherFile > lastSavedValue && lastFromOtherFile < currentValue))
                ) {
                    short temp = currentValue;
                    currentValue = lastFromOtherFile;
                    lastFromOtherFile = temp;
                    currentInputStream = changeStream(currentInputStream, tempFileStream1, tempFileStream2);
                }
                outputStream.writeShort(currentValue);
                lastSavedValue = currentValue;
            }
            outputStream.writeShort(lastFromOtherFile);

            //закончились данные из одного файла - добавляем из второго
            currentInputStream = changeStream(currentInputStream, tempFileStream1, tempFileStream2);
            while (currentInputStream.available() > 0) {
                outputStream.writeShort(currentInputStream.readShort());
            }
        }
    }

}
