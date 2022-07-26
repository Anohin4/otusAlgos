package main.bucketSort;

public class Bucket {

    private int valueStorage;
    private Bucket nextBucket;


    public Bucket(int i) {
        valueStorage = i;
    }

    public Bucket addToBucket(int intToAdd) {
        if(intToAdd <= valueStorage) {
            Bucket bucket = new Bucket(intToAdd);
            bucket.setNextBucket(this);
            return bucket;
        }
        Bucket bucket = nextBucket;
        while (true) {
            if(intToAdd <= bucket.getValueStorage()) {
                bucket.setNextBucket(bucket.getCopy());
                bucket.setValueStorage(intToAdd);
            } else {
                bucket = bucket.getNextBucket();
            }
        }
    }

    private Bucket getCopy() {
        Bucket bucket = new Bucket(this.valueStorage);
        bucket.setNextBucket(this.nextBucket);
        return bucket;
    }

    public void setNextBucket(Bucket nextBucket) {
        this.nextBucket = nextBucket;
    }

    public int getValueStorage() {
        return valueStorage;
    }

    public Bucket getNextBucket() {
        return nextBucket;
    }

    public void setValueStorage(int valueStorage) {
        this.valueStorage = valueStorage;
    }
}
