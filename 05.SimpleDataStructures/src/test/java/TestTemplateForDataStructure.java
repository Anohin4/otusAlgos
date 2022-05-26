package test.java;

import main.arrays.MyDataArray;

public class TestTemplateForDataStructure {

    public static void runIterationAddingTest(MyDataArray<Integer> array, int numberOfElements) {
        long start = System.currentTimeMillis();
        for(int i = 0; i != numberOfElements; i++) {
            array.add(i);
        }
        long resultTime = System.currentTimeMillis() - start;
        System.out.println(array.getClass() + " test result for "+ numberOfElements + " is " + resultTime + "ms");
    }

    public static void runRunnableTest(MyDataArray<Integer> array, Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long resultTime = System.currentTimeMillis() - start;
        System.out.println(array.getClass() + " test result "+  " is " + resultTime + "ms");
    }

}
