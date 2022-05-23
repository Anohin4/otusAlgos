package test.java.utils;

import static main.CountBytes.cashedPopcnt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import test.java.utils.Task;

public class ChessTestTemplate {

    public static void runTest(String catalogName, int amountOfTests, Function<Integer, Long> operator)
            throws IOException {
        runTest(catalogName, amountOfTests, 5000, operator);
    }
    public static void runTest(String catalogName, int amountOfTests, int timeout, Function<Integer, Long> operator)
            throws IOException {
        for (int i = 0; i < amountOfTests; i++) {
            Task task = getTask(catalogName, i);
            long myMask;

            //Запускаем тест, прерывая по  таймауту
            long startTime = System.currentTimeMillis();
            try {
                myMask = CompletableFuture
                        .supplyAsync(() -> operator.apply(task.getStartPosition()))
                        .get(timeout, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                System.out.printf("Unsuccessful test %d or time limit exceeded, stopped testing for that section", i);
                System.out.println();
                e.printStackTrace();
                break;
            }
            long timeOfComplete = System.currentTimeMillis() - startTime;

            checkAnswer(i, task, myMask, timeOfComplete);
        }


    }

    private static void checkAnswer(int i, Task task, long myMask, long timeOfComplete) {
        int myNumberOfMoves = cashedPopcnt(myMask);
        //Если получили какой-то результат - сравниваем и выводим на экран
        if (myNumberOfMoves == task.getNumberOfMoves() && myMask == task.getMask()) {
            System.out.println("Test " + i + " completed successfully. Time of complete "
                    + timeOfComplete + " ms");
        } else {
            System.out.println(
                    "Test " + i + " completed unsuccessfully. My answer:" + myMask + ", correct answer: "
                            + task.getMask());
        }
    }

    private static Task getTask(String catalogName, int n) throws IOException {
        Task result = new Task();
        String rootPath = System.getProperty("user.dir") + "/04.ByteArithmetic/src/test/resources/" + catalogName + "/test." + n;
        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(rootPath + ".in"))) {
            result.setStartPosition(Integer.parseInt(bufferedInputStream.readLine()));
        }
        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(rootPath + ".out"))) {
            result.setNumberOfMoves(Long.parseLong(bufferedInputStream.readLine()));
            result.setMask(Long.parseLong(bufferedInputStream.readLine()));
        } catch (NumberFormatException e) {
            System.out.println("Ignore test "+ n);
        }
        return result;

    }

}
