package main.radix;

import main.SortingAlg;

public class RadixSort implements SortingAlg {
    int[] arrayToSort;
    int maxValue;

    @Override
    public void sort(int[] array) {
        maxValue = findMaxValue(array);
        arrayToSort = array;
        int counter = 1;
        while(counter < maxValue) {
            radixSort(counter);
            counter *= 10;
        }
    }

    private void radixSort(int counter) {
        int[] countArray = new int[10];
        int[] resultArray = new int[arrayToSort.length];

        for(int i : arrayToSort) {
            int index = (i / counter) % 10;
            countArray[index] = countArray[index] + 1;
        }

        int count = 0;
        for (int i = 0; i < countArray.length; i++) {
            if (countArray[i] == 0) {
                continue;
            }
            countArray[i] = countArray[i] + count;
            count = countArray[i];
        }

        //делаем вставку
        for(int i = arrayToSort.length - 1; i >= 0; i--) {
            int currentValue = arrayToSort[i];
             int indexToInsert = countArray[(currentValue / counter) % 10] - 1;
            resultArray[indexToInsert] = currentValue;
            countArray[(currentValue / counter) % 10] = countArray[(currentValue / counter) % 10] - 1;
        }
        System.arraycopy(resultArray,0, arrayToSort,0, arrayToSort.length);
    }
}
