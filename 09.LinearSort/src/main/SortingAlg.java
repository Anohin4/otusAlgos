package main;

public interface SortingAlg {
    void sort(int[] array);

    default int findMaxValue(int[] array) {
        int maxIndex = 0;
        for (int i =  1; i < array.length; i++) {
            if(array[maxIndex] < array[i]) {
                maxIndex = i;
            }
        }
        return array[maxIndex];
    }
    default int findMinValue(int[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if(array[minIndex] > array[i]) {
                minIndex = i;
            }
        }
        return array[minIndex];
    }
}
