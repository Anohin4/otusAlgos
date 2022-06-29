package test.java;

import static test.java.SortTestTemplate.runTest;

import java.io.IOException;
import java.util.Arrays;
import main.bucketSort.BucketsSort;
import main.countSort.CountSort;
import main.radix.RadixSort;

public class Test {

    public static void main(String[] args) throws IOException {

        int[] test = {1,32,23,43,2,54,232,2,4,955,456,321};
        new RadixSort().sort(test);
        System.out.println(Arrays.toString(test));

        System.out.println(">>>BUCKET SORT<<<");
       // runTest(new BucketsSort());
        System.out.println(">>>COUNT SORT<<<");
        runTest(new CountSort());
        System.out.println(">>>RADIX SORT<<<");
        runTest(new RadixSort());
    }

}
