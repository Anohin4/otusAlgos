package test.java;

import static test.java.SortTestTemplate.getTask;
import static test.java.SortTestTemplate.runTest;

import java.io.IOException;
import java.util.Arrays;
import main.QuickSort;

public class Test {

    public static void main(String[] args) throws IOException {

        System.out.println(">>>HEAP SORT<<<");
        runTest(new QuickSort());
//        int[] array = getTask("revers", 4);
//        new QuickSort().sort(array);
//        System.out.println(Arrays.toString(array));
    }

}
