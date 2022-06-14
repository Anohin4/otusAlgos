package test.java;

import static main.FileGenerating.generateFileWithRandomShorts;
import static test.java.SortTestTemplate.runTest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import main.MergeSort;
import main.QuickSort;
import main.improvedExternalSort.ImprovedExternalSort;

public class Test {

    public static void main(String[] args) throws IOException {

        System.out.println(">>>Merge SORT<<<");
        runTest(new MergeSort());
        System.out.println(">>>Quick SORT<<<");
        runTest(new QuickSort());
    }

}
