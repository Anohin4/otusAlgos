package test.java;

import static test.java.SortTestTemplate.fileTesting;
import static test.java.SortTestTemplate.runTest;

import java.io.IOException;
import java.util.Arrays;
import main.bucketSort.BucketsSort;
import main.countSort.CountSort;
import main.radix.RadixSort;

public class Test {

    public static void main(String[] args) throws IOException {

        System.out.println(">>>BUCKET SORT<<<");
       runTest(new BucketsSort());
        System.out.println(">>>COUNT SORT<<<");
        runTest(new CountSort());
        System.out.println(">>>RADIX SORT<<<");
        runTest(new RadixSort());

        System.out.println("--------------File testing-------");
        fileTesting(new RadixSort());

    }

}
