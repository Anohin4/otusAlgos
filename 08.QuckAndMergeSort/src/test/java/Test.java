package test.java;

import static main.FileGenerating.generateFileWithRandomShorts;
import static test.java.SortTestTemplate.runTest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import main.improvedSort.ImprovedExternalSort;

public class Test {

    public static void main(String[] args) throws IOException {
        String filename = "input.txt";
        generateFileWithRandomShorts(1000, filename);
        new ImprovedExternalSort().sortFromFile(filename, 100);
        try(DataInputStream inputStream = new DataInputStream(new FileInputStream(filename))) {
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
