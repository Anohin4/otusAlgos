package test.java.primes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

public class TestPrimesTemplate {

    public static void runTest(String catalogName, int amountOfTests, UnaryOperator<Integer> operator) {
        runTest(catalogName, amountOfTests, 5000, operator);
    }
    public static void runTest(String catalogName, int amountOfTests, int timeout, UnaryOperator<Integer> operator) {
        for (int i = 0; i <= amountOfTests; i++) {
            int n;
            long correctAnswer;
            long myAnswer;

            //Берем данные по заданию из файлов
            try {
                n = getTestTaskWithOneNumber(catalogName, i);
                correctAnswer = getTestAnswer(catalogName, i);
            } catch (IOException e) {
                System.out.println("Can't find files for test " + i);
                continue;
            }

            //Запускаем тест, прерывая по  таймауту
            long startTime = System.currentTimeMillis();
            try {
                myAnswer = CompletableFuture
                        .supplyAsync(() -> operator.apply(n))
                        .get(timeout, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                System.out.printf("Unsuccessful test %d or time limit exceeded, stopped testing for that section", i);
                System.out.println();
                e.printStackTrace();
                break;
            }
            long timeOfComplete = System.currentTimeMillis() - startTime;

            //Если получили какой-то результат - сравниваем и выводим на экран
            if (myAnswer == correctAnswer) {
                System.out.println("Test " + i + " completed successfully. Answer:" + myAnswer + " time of complete "
                        + timeOfComplete + " ms");
            } else {
                System.out.println(
                        "Test " + i + " completed unsuccessfully. My answer:" + myAnswer + ", correct answer: "
                                + correctAnswer);
            }
        }


    }

    private static int getTestTaskWithOneNumber(String catalogName, int n) throws IOException {
        String pathFileIn =
                System.getProperty("user.dir") + "/03.AlgebraicAlgorithm/src/test/resources/" + catalogName + "/test." + n
                        + ".in";
        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(pathFileIn))) {
            return Integer.parseInt(bufferedInputStream.readLine());
        }
    }

    private static long getTestAnswer(String catalogName, int n) throws IOException {
        String pathFileIn =
                System.getProperty("user.dir") + "/03.AlgebraicAlgorithm/src/test/resources/" + catalogName + "/test." + n
                        + ".out";
        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(pathFileIn))) {
            return Long.parseLong(bufferedInputStream.readLine());
        }
    }

}
