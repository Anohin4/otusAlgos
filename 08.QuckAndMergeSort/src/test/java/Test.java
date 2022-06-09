package test.java;

import static test.java.SortTestTemplate.getTask;
import static test.java.SortTestTemplate.runTest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import main.ExternalSort;
import main.ExternalSort2;
import main.FileGenerating;
import main.FileGenerating2;
import main.MergeSort;
import main.QuickSort;

public class Test {

    public static void main(String[] args) throws IOException {
        new FileGenerating().generateFileWithRandomShorts();
        new ExternalSort().sortFromFile("input.txt");
        try(DataInputStream inputStream = new DataInputStream(new FileInputStream("input.txt"))) {
            while (inputStream.available() > 0) {
                System.out.println(inputStream.readShort());
            }
        }

//        System.out.println(">>>HEAP SORT<<<");
//        runTest(new MergeSort());
//        int[] array = getTask("revers", 4);
//        new QuickSort().sort(array);
//        System.out.println(Arrays.toString(array));
    }

}
