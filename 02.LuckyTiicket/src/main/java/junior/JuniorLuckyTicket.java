package main.java.junior;

public class JuniorLuckyTicket {

  public int getLuckyCounter() {
    int counter = 0;
    for(int a1 = 0 ;  a1 < 10; a1++) {
      for(int a2 = 0 ;  a2 < 10; a2++) {
        for(int a3 = 0 ;  a3 < 10; a3++) {
          int sumA = a1 + a2 + a3;
          for(int b1 = 0; b1 < 10; b1++) {
            for(int b2 = 0; b2 < 10; b2++) {
              int sumB = b1 + b2;
              int difference = sumA - sumB;
              if(difference < 10 && difference >= 0) {
                counter++;
              }
            }
          }
        }
      }
    }
    return counter;
  }

}
