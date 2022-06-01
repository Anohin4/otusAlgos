package test.java;

import static test.java.SortTestTemplate.runTest;

import java.io.IOException;
import java.util.Arrays;
import main.HeapSort;
import main.SelectionSort;

public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println(">>>SELECTION SORT<<<");
        runTest(new SelectionSort());
        System.out.println();
        System.out.println(">>>HEAP SORT<<<");
        runTest(new HeapSort());
//        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
//        new HeapSort().sort(array);
//        System.out.println(Arrays.toString(array));
    }

}
