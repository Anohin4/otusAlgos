package main;

public class MergeSort implements SortingAlg {

    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public void sort(short[] shortArray) {
        //костыльный метод для внешней сортировки
        int[] intArray = new int[shortArray.length];
        for (int i = 0; i < shortArray.length; i++)
            intArray[i] = shortArray[i];
        mergeSort(intArray, 0, shortArray.length - 1);
        for (int i = 0; i < shortArray.length; i++)
            shortArray[i] = (short) intArray[i];
    }

    private void mergeSort(int[] array, int low, int high) {
        int middle = (high + low) / 2;
        if (low >= high) {
            return;
        }
        mergeSort(array, low, middle);
        mergeSort(array, middle + 1, high);
        merge(array, low, middle, high);
    }

    private void merge(int[] arrayToSort, int low, int middle, int high) {
        int[] tempArray = new int[high - low + 1];
        int a = low;
        int b = middle + 1;
        int currentIndexOfTempArray = 0;
        //сортируем два массива
        while (a <= middle && b <= high) {
            if (arrayToSort[a] < arrayToSort[b]) {
                tempArray[currentIndexOfTempArray++] = arrayToSort[a++];
            } else {
                tempArray[currentIndexOfTempArray++] = arrayToSort[b++];
            }
        }
        //добавляем в массив значения, которые могли остаться
        while (a <= middle) {
            tempArray[currentIndexOfTempArray++] = arrayToSort[a++];
        }
        while (b <= high) {
            tempArray[currentIndexOfTempArray++] = arrayToSort[b++];
        }

        //копируем в итоговый массив
        System.arraycopy(tempArray, 0, arrayToSort, low, high + 1 - low);

    }
}
