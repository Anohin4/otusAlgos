package main;

public class ImprovedQuickSort implements SortingAlg{

    @Override
    public void sort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private void quicksort(int[] array, int low, int high) {
        int m = low - 1;
        int i = low;
        int pivot = array[high];

        for (;i < array[high]; i ++) {
            if (array[i] < array[pivot]) {
                swap(array, ++m, i);
            }
        }
        quicksort(array, m + 1, high);
        quicksort(array, low, m - 1);
    }
}
