package main;

public class SelectionSort implements SortingAlg{

    @Override
    public void sort(int[] array) {
        int sortedPart = array.length;
        for (int i = 0; i < sortedPart; sortedPart--) {
            swap(array, findMaxIndex(array, 0, sortedPart - 1), sortedPart - 1);
        }

    }
    private int findMaxIndex(int[] array, int indexStart, int indexEnd) {
        int maxIndex = 0;
        for (int i = indexStart + 1; i <= indexEnd; i++) {
            if(array[maxIndex] < array[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
