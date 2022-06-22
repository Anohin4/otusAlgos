package main;

import java.util.Arrays;
import main.bucketSort.BucketsSort;
import main.countSort.CountSort;
import main.radix.RadixSort;

public class Test {

    public static void main(String[] args) {
        int[] q = {4,2,1,5,7,9, 8, 6, 8};
        new RadixSort().sort(q);
        System.out.println(Arrays.toString(q));
    }

}
