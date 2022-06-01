package main;

public class HeapSort implements SortingAlg {

    @Override
    public void sort(int[] array) {
        //создаем начальный хип
        for (int root = array.length / 2 - 1; root >= 0; root--) {
            heapify(array, root, array.length);
        }
        //в корне всегда наибольший элемент, меняем его с последним не сортированным и восстанавливаем структуру
        //каждую итерацию исключаем последний элемент из дерева
        for (int j = array.length - 1; j > 0; j--) {
            swap(array,0, j);
            heapify(array,0, j);
        }
    }

    public void heapify(int[] array, int root, int arraySize) {
        int l = 2 * root + 1;
        int r = l + 1;
        int x = root;
        //находим максимум из трех чисел, корня, и л р
        x = l < arraySize && array[l] > array[x] ? l : x;
        x = r < arraySize && array[r] > array[x] ? r : x;
        if (x == root) {
            return;
        }
        swap(array, x, root);
        heapify(array, x, arraySize);
    }
}
