package test.java;

import main.arrays.MyDataArray;

public class TestTemplateForDataStructure {

    public static void runTest(Class clazz, Runnable runnable) {
        long start = System.currentTimeMillis();
        for (int i = 0; i != 100000; i++) {
            runnable.run();
        }
        long resultTime = System.currentTimeMillis() - start;
        System.out.println(clazz + " test result is " + resultTime + "ms");
    }

    public static void runRunnableTest(MyDataArray<Integer> array, Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long resultTime = System.currentTimeMillis() - start;
        System.out.println(array.getClass() + " test result " + " is " + resultTime + "ms");
    }

}
