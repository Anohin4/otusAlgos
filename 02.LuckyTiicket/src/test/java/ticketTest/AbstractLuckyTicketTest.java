package test.java.ticketTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public abstract class AbstractLuckyTicketTest{
  public void runTests() {
    getTicket();
  }

  protected void getTicket() {
    for (int i = 0; i <= 9; i++){
      int n;
      long correctAnswer;
      long myAnswer;

      //Берем данные по заданию из файлов
      try {
        n =getTestTask(i);
        correctAnswer = getTestAnswer(i);
      } catch (IOException e) {
        System.out.println("Can't find files for test " + i);
        continue;
      }

      //Запускаем тест, прерывая по  таймауту
      long startTime = System.currentTimeMillis();
      try {
        myAnswer = CompletableFuture
            .supplyAsync(() -> getLuckyCounter(n))
            .get(5000, TimeUnit.MILLISECONDS);
      } catch (Exception e) {
        System.out.printf("Unsuccessful test %d or time limit exceeded, stopped testing for that section", n);
        System.out.println();
        break;
      }
      long timeOfComplete = System.currentTimeMillis() - startTime;

      //Если получили какой-то результат - сравниваем и выводим на экран
      if(myAnswer == correctAnswer) {
        System.out.println("Test " + i + " completed successfully. Answer:" + myAnswer + " time of complete " + timeOfComplete + " ms");
      } else {
        System.out.println("Test " + i + " completed unsuccessfully. My answer:" + myAnswer + ", correct answer: " + correctAnswer);
      }
    }


  }

  private int getTestTask(int n) throws IOException {
    String pathFileIn = System.getProperty("user.dir") + "/02.LuckyTiicket/src/test/resources/test." + n + ".in";
    try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(pathFileIn))) {
      return Integer.parseInt(bufferedInputStream.readLine());
    }
  }

  private long getTestAnswer(int n) throws IOException {
    String pathFileIn = System.getProperty("user.dir") + "/02.LuckyTiicket/src/test/resources/test." + n + ".out";
    try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(pathFileIn))) {
      return Long.parseLong(bufferedInputStream.readLine());
    }
  }

  abstract long getLuckyCounter(int n);

}
