package test.java;

import test.java.ticketTest.AdvancedRecursiveTest;
import test.java.ticketTest.SimpleRecursiveTest;

public class MainTest {

  public static void main(String[] args) {
    SimpleRecursiveTest simpleRecursiveTest = new SimpleRecursiveTest();
    AdvancedRecursiveTest advancedRecursiveTest = new AdvancedRecursiveTest();
    System.out.println("-----------Simple recursive tests -------------");
    simpleRecursiveTest.runTests();
    System.out.println("-----------Advanced lucky ticket counter tests -------------");
    advancedRecursiveTest.runTests();
  }

}
