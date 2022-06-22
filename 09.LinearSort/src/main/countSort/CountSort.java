package main.countSort;

import main.SortingAlg;

public class CountSort implements SortingAlg {

    @Override
    public void sort(int[] array) {
        int[] storage = new int[findMaxValue(array) + 1];
        int[] resultArray = new int[array.length];
        //получаем количество каждого элемента
        for (int i : array) {
            storage[i] = storage[i] + 1;
        }

        //получаем индексы
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == 0) {
                continue;
            }
            storage[i] = storage[i] + count;
            count = storage[i];
        }

        //делаем вставку
        for(int i = array.length - 1; i >= 0; i--) {
            int currentValue = array[i];
            int indexToInsert = storage[currentValue] - 1;
            resultArray[indexToInsert] = currentValue;
            storage[currentValue] = storage[currentValue] - 1;
        }
        System.arraycopy(resultArray,0,array,0,array.length);
    }
}
