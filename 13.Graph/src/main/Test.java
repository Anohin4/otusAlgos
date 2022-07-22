package main;

import java.util.Arrays;
import main.model.Graph;

public class Test {

    public static void main(String[] args) {
        runTests(5);
    }

    public static void runTests(int numberOfTests) {
        for (int i = 1; i<= numberOfTests; i++) {
            System.out.println("Test " + i);
            String path = "13.Graph/src/resources/graph" + i + ".txt";
            int[][] ints = Utils.readGraphFromFile(path);
            System.out.println(Arrays.deepToString(ints));
            boolean isDirected = false;
            if (i > 3) {
                isDirected = true;
            }
            Graph graph = new Graph(ints, isDirected);
            Utils.printMatrix(graph.getAdjacentyMatrix());
        }
    }

}
