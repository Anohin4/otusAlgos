package main;

public class BinaryInsertionSort implements SortingAlg{

    @Override
    public void sort(int[] array) {
        int lastSortedIndex = 0;
        int i = array.length - 1;
        while (i > lastSortedIndex) {
            int valueToInsert = array[i];
            int insertIndex = findPosition(array, valueToInsert, lastSortedIndex);
            System.arraycopy(array, insertIndex,
                    array, insertIndex + 1, i - insertIndex);
            lastSortedIndex++;
            array[insertIndex] = valueToInsert;
        }
    }

    public int findPosition(int[] array, int valueToInsert, int lastSortedIndex) {
        int high = lastSortedIndex;
        int low = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = array[mid];
            if(midValue > valueToInsert) {
                high = mid - 1;
            } else if (midValue < valueToInsert) {
                low = mid + 1;
            } else {
                return mid;
            }

        }
        return high + 1;
    }

}
