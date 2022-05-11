package main.java.middle;

public class AdvancedLuckyTicket {

  public long getLuckyCounter(int n) {
    long result = 0;
    int[] arr = new int[11];
    for (int i = 0; i < 10; i++) {
      arr[i] = 1;
    }
    for (int j = 0; j < (n - 1); j++) {
      arr = getNextArr(arr);
    }

    for (int i = 0; i< arr.length - 1; i++) {
      result += Math.pow(arr[i], 2);
    }
    return result;

  }

  private int[] getNextArr(int[] prevArr) {
    int newLen = prevArr.length + 9;
    int[] resultArr = new int[newLen];
    for (int i = 0; i < newLen; i++) {
      int q = 0;
      for (int j = 0; j < 10; j++) {
        if (i - j >= 0 && i - j < prevArr.length) {
          q += prevArr[i - j];
        }
      }
      resultArr[i] = q;
    }
    return resultArr;
  }



}


