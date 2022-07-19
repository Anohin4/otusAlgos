package hashtable;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class MyHashTable<K, V> {
    private Bucket<K,V>[] buckets;
    private int size;
    private final float percentOfOccupancy = 0.75f;

    public MyHashTable() {
        this.buckets = new Bucket[8];
        size = 0;
    }

    public void put(K key, V value) {
        addObjectsToBucketArray(buckets, key, value);
        size++;
        if(size > buckets.length * percentOfOccupancy) {
            rehashTable();
        }
    }

    public boolean remove(K key) {
        int hashNumber = hash(key);
        Bucket bucket = buckets[hashNumber];
        if(nonNull(bucket)) {
            boolean result = bucket.remove(key);
            if(result) {
                size--;
            }
            return result;
        }
        return false;
    }

    public Object get(K key) {
        int hashNumber = hash(key);
        Bucket bucket = buckets[hashNumber];
        if(nonNull(bucket)) {
            return bucket.get(key);
        }
        return null;
    }
    private void addObjectsToBucketArray(Bucket[] bucketArray, K key, V value) {
        int hashNumber = hash(key, bucketArray.length);
        Bucket bucket = bucketArray[hashNumber];
        if(isNull(bucket)) {
            bucketArray[hashNumber] = new Bucket(key,value);
        } else {
            bucket.insert(key,value);
        }
    }

    private void rehashTable() {
        Bucket<K,V>[] resultArray = new Bucket[buckets.length * 2];
        for (Bucket<K, V> bucket : buckets) {
            if(isNull(bucket)) {
                continue;
            }
            while (true) {
                //контейнер не удален - добавляем
                if(!bucket.isDeleted()) {
                    K key = bucket.getKey();
                    V value = bucket.getValue();
                    addObjectsToBucketArray(resultArray, key, value);
                }
                //больше контейнеров нет - прерываем цикл
                if(isNull(bucket.getNextBucket())) {
                    break;
                }
                bucket = bucket.getNextBucket();
            }
        }
        buckets = resultArray;
    }

    private int hash(K key) {
        return hash(key, buckets.length);
    }
    private int hash(K key, int sizeOfArray) {
       return key.hashCode() % sizeOfArray;
    }

    public int getSize() {
        return size;
    }
}
