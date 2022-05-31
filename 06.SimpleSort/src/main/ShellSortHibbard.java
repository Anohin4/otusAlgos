package main;

public class ShellSortHibbard implements SortingAlg{

    @Override
    public void sort(int[] array) {
        int k = findDividers(array.length);
        int gap = (int) Math.pow(2, k) - 1;
        while(k > 0) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j >=gap && array[j - gap] > array[j]; j-= gap) {
                    int temp = array[j];
                    array[j] = array[j - gap];
                    array[j-gap] = temp;
                }
            }
            k--;
            gap = (int) Math.pow(2, k) - 1;
        }
    }

    private int findDividers(int arrayLen) {
        int k = 1;
        int gap = 1;
        while (gap < arrayLen) {
            k++;
            gap = (int) Math.pow(2, k) - 1;
        }
        return k - 1;

    }

}
