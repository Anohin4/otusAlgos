package main;

public interface SortingAlg {
    void sort(int[] array);

    default int findMaxValue(int[] array) {
        int maxIndex = 0;
        for (int i = 0 + 1; i < array.length; i++) {
            if(array[maxIndex] < array[i]) {
                maxIndex = i;
            }
        }
        return array[maxIndex];
    }
}
