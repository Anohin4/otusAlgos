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
        radixSort(counter);
    }

    private void radixSort(int counter) {
        //условие выхода рекурсии
        if(counter > maxValue) {
            return;
        }

        int[] countArray = new int[10];
        int[] resultArray = new int[arrayToSort.length];
        int count = 0;
        for(int i : arrayToSort) {
            int index = (i / counter) % 10;
            countArray[index] = countArray[index] + 1;
        }

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
            int indexToInsert = countArray[currentValue] - 1;
            resultArray[indexToInsert] = currentValue;
            countArray[currentValue] = countArray[currentValue] - 1;
        }
        System.arraycopy(resultArray,0, arrayToSort,0, arrayToSort.length);
        //рекурсивно вызываем дальше
        radixSort(counter * 10);
    }
}
