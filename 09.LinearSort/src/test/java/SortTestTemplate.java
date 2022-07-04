package test.java;

import static main.FileGenerating.generateFileWithRandomInt;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import main.SortingAlg;


public class SortTestTemplate {

    public static void runTest(SortingAlg alg)
            throws IOException {
        int numberOfTests = 8;
        System.out.println("------RANDOM TEST-----");
        runTest("random", numberOfTests, alg);
        System.out.println("------DIGITS TEST-----");
        runTest("digits", numberOfTests, alg);
        System.out.println("------REVERS TEST-----");
        runTest("revers", numberOfTests, alg);
        System.out.println("------SORTED TEST-----");
        runTest("sorted", numberOfTests, alg);
    }

    public static void fileTesting(SortingAlg alg) throws IOException {
        int amountOfIntegers = 1_00_000_000;
        generateFileWithRandomInt(amountOfIntegers, "test.txt");
        int[] array = new int[amountOfIntegers];
        try(DataInputStream inputStream = new DataInputStream(new FileInputStream(new File("test.txt")))) {
            for (int i = 0; i< amountOfIntegers; i++) {
                array[i]=inputStream.readInt();
            }
        }
        long startTime = System.currentTimeMillis();
        alg.sort(array);
        long timeOfComplete = System.currentTimeMillis() - startTime;
        System.out.println(Arrays.toString(array));
        System.out.println("time of completion " + timeOfComplete);

    }

    public static void runTest(String catalogName, int amountOfTests, SortingAlg alg)
            throws IOException {
        for (int i = 0; i < amountOfTests; i++) {
            int[] task = getTask(catalogName, i);
            int[] answer = getAnswer(task.length, catalogName, i);

            //Запускаем тест, прерывая по  таймауту
            long startTime = System.currentTimeMillis();
            alg.sort(task);
            long timeOfComplete = System.currentTimeMillis() - startTime;
            checkAnswer(task, answer, timeOfComplete, i);
        }
    }

    private static void checkAnswer(int[] task, int[] answer, long timeOfComplete, int numberOfTest) {
        int compare = Arrays.compare(task, answer);
        if (compare == 0) {
            System.out.println("Test with 10^" + numberOfTest + " elements completed successfully. Time of complete "
                    + timeOfComplete + " ms");
        } else {
            System.out.println("Test " + numberOfTest + " failed. Time of complete "
                    + timeOfComplete + " ms");
        }
    }

    public static int[] getTask(String catalogName, int numberOfTest) throws IOException {
        String rootPath = System.getProperty("user.dir") + "/06.SimpleSort/src/test/resources/" + catalogName + "/test."
                + numberOfTest
                + ".in";
        Scanner scanner = new Scanner(new File(rootPath));
        int[] array = new int[scanner.nextInt()];
        int i = 0;
        while (scanner.hasNextInt()) {
            array[i++] = scanner.nextInt();
        }
        return array;
    }

    private static int[] getAnswer(int length, String catalogName, int numberOfTest) throws FileNotFoundException {
        String rootPath = System.getProperty("user.dir") + "/06.SimpleSort/src/test/resources/" + catalogName + "/test."
                + numberOfTest + ".out";
        Scanner scanner = new Scanner(new File(rootPath));
        int[] array = new int[length];
        int i = 0;
        while (scanner.hasNextInt()) {
            array[i++] = scanner.nextInt();
        }
        return array;
    }

}
