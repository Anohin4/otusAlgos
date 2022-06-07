package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalSort {

    private File tempOneFile = new File("temp1.txt");
    private File tempTwoFile = new File("temp2.txt");

    public void sortFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        while (true) {
            externalSort(file);
            if (isSorted()) {
                break;
            }
            mergeTempFiles(file);
        }
    }

    private void externalSort(File inputFile) throws IOException {
        //удвляем старые файлы
        tempOneFile.delete();
        tempTwoFile.delete();
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(inputFile));
                DataOutputStream tempFileStream1 = new DataOutputStream(new FileOutputStream(tempOneFile));
                DataOutputStream tempFileStream2 = new DataOutputStream(new FileOutputStream(tempTwoFile))) {
            System.out.println("size " + inputStream.available());
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

    private boolean isSorted() {
        return tempTwoFile.length() == 0;
    }

    private <T> T changeStream(T currentStream, T tempFileStream1,
            T tempFileStream2) {
        if (currentStream == tempFileStream1) {
            return tempFileStream2;
        } else {
            return tempFileStream1;
        }
    }

    private void mergeTempFiles(File inputFile) throws IOException {
        inputFile.delete();
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(inputFile));
                DataInputStream tempFileStream1 = new DataInputStream(new FileInputStream(tempOneFile));
                DataInputStream tempFileStream2 = new DataInputStream(new FileInputStream(tempTwoFile))) {
            System.out.println("temp 1 " +tempFileStream1.available());
            System.out.println("temp 2 " +tempFileStream2.available());

            DataInputStream currentInputStream = tempFileStream1;



            short previousValue = currentInputStream.readShort();
            outputStream.writeShort(previousValue);

            short lastFromOtherFile = tempFileStream2.readShort();
            while (currentInputStream.available() > 0) {
                short currentValue = currentInputStream.readShort();
                if (currentValue < previousValue && currentValue < lastFromOtherFile) {
                    //если начинаем серию заново и значение из текущего массива больше значения прошлого - меняем их и поток
                    short temp = currentValue;
                    currentValue = lastFromOtherFile;
                    lastFromOtherFile = temp;
                    currentInputStream = changeStream(currentInputStream, tempFileStream1, tempFileStream2);
                }
                outputStream.writeShort(currentValue);
                previousValue = currentValue;
            }

            //закончились данные из одного файла - добавляем из второго
            currentInputStream = changeStream(currentInputStream,tempFileStream1,tempFileStream2);
            while (currentInputStream.available() > 0) {
                outputStream.writeShort(currentInputStream.readShort());
            }
        }
    }

}
