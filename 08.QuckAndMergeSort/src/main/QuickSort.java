package main;

public class QuickSort implements SortingAlg {

    @Override
    public void sort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    public void quicksort(int[] array, int low, int high) {
        if (array.length == 0 || low >= high) {
            return;
        }
        int i = low;
        int j = high;
        int pivot = array[high];

        while (i <= j) {
            //подготавливаем данные для свопа
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        if (low < j) {
            quicksort(array, low, j);
        }
        if (high > i) {
            quicksort(array, i, high);
        }
    }
}
