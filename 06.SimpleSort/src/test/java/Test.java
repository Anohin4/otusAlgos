package test.java;

import static test.java.SortTestTemplate.runTest;

import java.io.IOException;
import main.BinaryInsertionSort;
import main.BubbleSort;
import main.InsertionSort;
import main.ShellSort;
import main.ShellSortHibbard;

public class Test {

    public static void main(String[] args) throws IOException {
        System.out.println(">>>>>>>BUBLE<<<<<<<<<<<<<<");
        runTest(new BubbleSort());
       System.out.println();
        System.out.println(">>>>>>>INSERTION<<<<<<<<<<<<<<");
        runTest(new InsertionSort());
        System.out.println();
        System.out.println(">>>>>>>BINARY INSERTON <<<<<<<<<<<<<<");
        runTest(new BinaryInsertionSort());
        System.out.println();
        System.out.println(">>>>>>>SHELL<<<<<<<<<<<<<<");
        runTest(new ShellSort());
        System.out.println(">>>>>>>SHELL HIBBARD<<<<<<<<<<<<<<");
        runTest(new ShellSortHibbard());

    }

}
