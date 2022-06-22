package main.bucketSort;

import static java.util.Objects.nonNull;

public class Bucket {

    private int storage;

    private Bucket nextBucket;

    public Bucket(int storage) {
        this.storage = storage;
    }

    public int getStorageValue() {
        return storage;
    }

    public Bucket getNextBucket() {
        return nextBucket;
    }

    public int getStorageValueAndIterate() {
        int temp  = getStorageValue();
        //если есть следующее ведро - менем значение текущего на него
        if(nonNull(nextBucket)) {
            this.storage = nextBucket.getStorageValue();
            this.nextBucket = nextBucket.getNextBucket();
        }
        return temp;
    }

    public void setNextBucket(Bucket nextBucket) {
        this.nextBucket = nextBucket;
    }

    public Bucket addBucket(Bucket bucketToAdd) {
        //рекурсивно заполняем ведра по цепочке, попутно сравнивая
        if (bucketToAdd.getStorageValue() < getStorageValue()) {
            bucketToAdd.setNextBucket(this);
            return bucketToAdd;
        } else if (!nonNull(nextBucket)){
            setNextBucket(bucketToAdd);
            return this;
        } else {
            setNextBucket(getNextBucket().addBucket(bucketToAdd));
            return this;
        }
    }
}
