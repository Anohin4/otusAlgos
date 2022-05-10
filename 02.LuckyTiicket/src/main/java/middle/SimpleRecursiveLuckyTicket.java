package main.java.middle;

public class SimpleRecursiveLuckyTicket {

  private long counter = 0;


  public long getLuckyСounter(int n) {
    counter = 0; //обнуление счетчика на всякий случай
    getL(n, 0, 0);
    return counter;

  }

  private void getL(int n, int sumA, int sumB) {
    if (n == 0) {
      if (sumA == sumB) {
        counter++;
      }
      return;
    }
    for (int a = 0; a < 10; a++) {
      for (int b = 0; b < 10; b++) {
        getL(n - 1, sumA + a, sumB + b);
      }
    }


  }

}
