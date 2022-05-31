public class InsertionSort {

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
        for (int i = lastSortedIndex; i > 0; i--) {
            if(array[i] < valueToInsert) {
                return i + 1;
            }
        }
        return 0;
    }
}
