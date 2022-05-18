package test.java.power;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class TestPowTemplate {

    public static void runPowerTest(String catalogName, int amountOfTests, BiFunction<Double, Double, Double> biFunction) {
        runPowerTest(catalogName, amountOfTests, 20000, biFunction);
    }
    public static void runPowerTest(String catalogName, int amountOfTests, int timeout, BiFunction<Double, Double, Double> biFunction) {
        for (int i = 0; i <= amountOfTests; i++) {
            String[] n;
            double correctAnswer;
            double myAnswer;

            //Берем данные по заданию из файлов
            try {
                n = getTestTask(catalogName, i);
                correctAnswer = getTestAnswer(catalogName, i);
            } catch (IOException e) {
                System.out.println("Can't find files for test " + i);
                continue;
            }

            //Запускаем тест, прерывая по  таймауту
            long startTime = System.currentTimeMillis();
            try {
                myAnswer = CompletableFuture
                        .supplyAsync(() -> biFunction.apply(Double.parseDouble(n[0]), Double.parseDouble(n[1])))
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
                                + correctAnswer + " time of complete "
                                + timeOfComplete + " ms");
            }
        }


    }

    private static String[] getTestTask(String catalogName, int n) throws IOException {
        String pathFileIn =
                System.getProperty("user.dir") + "/03.AlgebraicAlgorithm/src/test/resources/" + catalogName + "/test." + n
                        + ".in";
        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(pathFileIn))) {
            String[] result = new String[2];
            result[0] = bufferedInputStream.readLine();
            result[1] = bufferedInputStream.readLine();
            return result;
        }
    }

    private static double getTestAnswer(String catalogName, int n) throws IOException {
        String pathFileIn =
                System.getProperty("user.dir") + "/03.AlgebraicAlgorithm/src/test/resources/" + catalogName + "/test." + n
                        + ".out";
        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(pathFileIn))) {
            return Double.parseDouble(bufferedInputStream.readLine());
        }
    }

}
