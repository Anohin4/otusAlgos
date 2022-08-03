package main.topologicSort;

import java.util.Arrays;

public class Demucron {

    public static int[][] topologicalSort(int[][] initialMatrix) {
        int level = 0;
        //делаем копию матрицы, что бы не менять изначальную
        //если матрицу можно менять, то можно обойтись и без этого
        //последний массив хранит иформацию о сумме каждого из массивов
        int[][] workMatrix = Arrays.copyOf(initialMatrix, initialMatrix.length);
        int[] arrayWithSumm = new int[workMatrix.length];
        int[][] result = new int[initialMatrix.length][initialMatrix.length];
        //-1 будет выполнять функцию null
        for (int i = 0; i < initialMatrix.length; i++) {
            Arrays.fill(result[i], -1);
        }

        //суммируем начальные значения массивов Х
        for (int i = 0; i < workMatrix.length; i++) {
            calculateColumnSumm(workMatrix, arrayWithSumm, i);
        }

        while (level < initialMatrix.length) {
            int counter = 0;
            for (int i = 0; i < workMatrix.length; i++) {
                if (arrayWithSumm[i] == 0) {
                    result[level][counter] = i;
                    counter++;
                }
            }
            deleteRows(workMatrix, arrayWithSumm, result[level]);
            level++;
        }
        return result;
    }

    private static void deleteRows(int[][] workMatrix, int[] arrayWithSumm, int[] rowsToDelete) {
        for (int column : rowsToDelete) {
            if (column == -1) {
                break;
            }
            //удаляем из матрицы (проставляем везде 0) обозначенную колону
            int[] array = workMatrix[column];
            for (int i = 0; i < array.length; i++) {
                if (array[i] > 0) {
                    int rowValue = array[i];
                    array[i] = 0;
                    //пересчитываем сумму значений для колоны после удаления
                    arrayWithSumm[i] = arrayWithSumm[i] - rowValue;
                }
            }
            //убираем данную колону из алгоритма
            arrayWithSumm[column] = -1;
        }
    }

    private static void calculateColumnSumm(int[][] workMatrix, int[] arrayWithSumm, int column) {
        int summ = 0;
        for (int[] i : workMatrix) {
            summ += i[column];
        }
        arrayWithSumm[column] = summ;
    }
}
