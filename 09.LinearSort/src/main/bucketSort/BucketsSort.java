package main.bucketSort;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import main.SortingAlg;

public class BucketsSort implements SortingAlg {

    Bucket[] bucketStorage;


    @Override
    public void sort(int[] array) {
        bucketStorage = new Bucket[array.length];
        int max = findMaxValue(array);
        for (int j : array) {
            int bucketIndex = (int) (((long) j * (long) array.length) / ((long) (max + 1)));
            addToBucket(bucketIndex, j);
        }

        //вынимаем и добавляем в итоговый массив
        int i = 0;
        for (Bucket bucket : bucketStorage) {
            if (isNull(bucket)) {
                continue;
            }
            if (bucket.getSize() == 1) {
                array[i++] = bucket.getFirst();
            } else {
                int[] storage = bucket.getStorage();
                int size = bucket.getSize();
                System.arraycopy(storage, 0, array, i, size);
                i += size;
            }
        }
    }

    private void addToBucket(int bucketIndex, int value) {
        Bucket bucket = bucketStorage[bucketIndex];
        if (nonNull(bucket)) {
            bucket.addToBucket(value);
        } else {
            bucketStorage[bucketIndex] = new Bucket(value);
        }
    }
}
