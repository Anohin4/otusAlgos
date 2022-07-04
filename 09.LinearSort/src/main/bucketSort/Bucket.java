package main.bucketSort;

public class Bucket {

    private int[] storage = new int[8];
    private int size;

    public int[] getStorage() {
        return storage;
    }

    public int getSize() {
        return size;
    }

    public Bucket(int i) {
        storage[0] = i;
        size = 1;
    }

    public void addToBucket(int intToAdd) {
        int insertIndex = 0;
        //находим индекс вставки
        for (; insertIndex < size; insertIndex++) {
            if(storage[insertIndex] >= intToAdd)
                break;
        }

        System.arraycopy(storage, insertIndex,
                storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = intToAdd;
        size++;
        resize();
    }

    public int getFirst() {return storage[0];};
    private void resize() {
        if(storage.length == size) {
            int[] newArray = new int[storage.length*2];
            System.arraycopy(storage, 0,
                    newArray, 0, storage.length);
            storage = newArray;
        }
    }
}
