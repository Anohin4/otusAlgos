package main;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import main.model.Graph;
import main.topologicSort.Demucron;
import main.topologicSort.Tarjan;

public class Test {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        String path = "13.Graph/src/resources/graph6.txt";
        int[][] ints = Utils.readMatrixFromEdgesList(path, true);
        //System.out.println(Arrays.deepToString(Demucron.topologicalSort(ints)));
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
            Graph graph = new Graph(ints, isDirected);
            Utils.printMatrix(graph.getAdjacentyMatrix());
        }
    }

    public static  Object getFieldValue(Object object, String fieldName) throws Exception {
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName) && !Modifier.isPrivate(field.getModifiers())) {
                return field.get(object);
            }
        }
        return null;
    }

}
