package main.bucketSort;

import static java.util.Objects.nonNull;

import main.SortingAlg;

public class BucketsSort implements SortingAlg {

    Bucket[] bucketStorage;


    @Override
    public void sort(int[] array) {
        bucketStorage = new Bucket[array.length];
        int max = findMaxValue(array);
        for (int j : array) {
            int bucketIndex = (j * array.length) / (max + 1);
            addToBucket(bucketIndex, j);
        }

        int i = 0;
        for (Bucket bucket : bucketStorage) {
            if (!nonNull(bucket)) {
                continue;
            }
            //если цепочка ведер -  добавляем значения и итерируем по цепочке
            while (nonNull(bucket.getNextBucket())) {
                array[i++] = bucket.getStorageValueAndIterate();
            };
            //добавляем последнее значение
            array[i++] = bucket.getStorageValue();
        }
    }



    private void addToBucket(int bucketIndex, int value) {
        Bucket newBucket = new Bucket(value);
        Bucket bucket = bucketStorage[bucketIndex];
        if(nonNull(bucket)) {
            bucketStorage[bucketIndex] = bucket.addBucket(newBucket);
        } else {
            bucketStorage[bucketIndex] = newBucket;
        }
    }
}
