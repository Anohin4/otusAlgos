package main;

public interface SortingAlg {
    void sort(int[] array);

    default void swap(int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

}
