package main.topologicSort;

import java.util.Arrays;

public class Tarjan {
    private static int[] result;
    private static int[] notProcessed;
    private static int[] inProcess;
    private static int[] processed;

    private static int processedEdges;
    public static int[] topologicalSort(int[][] matrix) {
        result = new int[matrix.length];
        notProcessed = new int[matrix.length];
        inProcess = new int[matrix.length];
        processed = new int[matrix.length];
        Arrays.fill(notProcessed, 1);
        //номер в массиве соответствует номеру ребра
        //если в массиве стоит единица - в этом массиве используется это ребро
        //например если в массиве inProcess[3] == 1, то значит, что ребро с номером 3 сейчас в обработке
        // так же это значит, что в массивах notProcessed и processed [3] == 0

        int currentEdge = 0;
        processedEdges = 0;
        while (processedEdges < matrix.length) {
            currentEdge = findEdgeToStartFrom(matrix, currentEdge);
            result[processedEdges] = currentEdge;
            inProcess[currentEdge] = 1;
            notProcessed[currentEdge] = 0;
            processed[currentEdge] = 0;
            processedEdges++;
            findTopologicalOrder(matrix, currentEdge);
        }

        return result;
    }

    private static void findTopologicalOrder(int[][] matrix, int currentEdge) {
        int[] currentArray = matrix[currentEdge];
        for(int i = 0; i< currentArray.length; i++) {
            if(currentArray[i] == 1) {
                //проверяем на цикл
                if(inProcess[i] == 1) {
                    throw new RuntimeException("У нас тут цикл " + Arrays.toString(result));
                }
                if (notProcessed[i] == 1) {
                    notProcessed[i] = 0;
                    inProcess[i] = 1;
                    result[processedEdges] = i;
                    processedEdges++;
                    findTopologicalOrder(matrix, i);
                }
            }
        }
        //обошли весь цикл, помечаем данное ребро как завершенное
        processed[currentEdge] = 1;
        inProcess[currentEdge] = 0;
    }
    private static int findEdgeToStartFrom(int[][] matrix, int start) {
        for (; start < matrix.length; start++) {
            if(notProcessed[start] == 1 && calculateArray(matrix, start) == 0) {
                return start;
            }
        }
        return start;
    }
    private static int calculateArray(int[][] matrix, int edge) {
        int i = 0;
        for(int[] arrays : matrix) {
            i += arrays[edge];
        }
        return i;
    }



}
