package main;

public class ShellSort implements SortingAlg{

    @Override
    public void sort(int[] array) {
        int gap = array.length / 2;
        for(; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j >=gap && array[j - gap] > array[j]; j-= gap) {
                    int temp = array[j];
                    array[j] = array[j - gap];
                    array[j-gap] = temp;
                }
            }

        }
    }

}
