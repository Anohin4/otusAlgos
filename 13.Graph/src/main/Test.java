package main;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import main.minSpaningTree.Kruskal;
import main.model.arrays.graphs.GraphWithVertex;
import main.topologicSort.Tarjan;

public class Test {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        String path = "13.Graph/src/resources/graph3.txt";
        int[][] ints = Utils.readMatrixFromEdgesList(path, true);
        System.out.println(Arrays.deepToString(Kruskal.getMinSpanningTree(ints)));
        System.out.println(Arrays.toString(Tarjan.topologicalSort(ints)));
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
            GraphWithVertex graphWithVertex = new GraphWithVertex(ints, isDirected);
            Utils.printMatrix(graphWithVertex.getAdjacentyMatrix());
        }
    }

}
