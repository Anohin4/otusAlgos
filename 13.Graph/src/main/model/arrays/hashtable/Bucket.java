package main.model.arrays.hashtable;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Bucket<K, V> {
    private V value;
    private K key;
    private Bucket<K,V> nextBucket;
    private boolean isDeleted;

    public Bucket(K key, V value) {
        this.key = key;
        this.value = value;
        this.isDeleted = false;
    }

    public void insert(K keyToInsert, V valueToStore) {
        //добавляем только в двух случаях
        //1 - ключ в ячейке сопадает с нужным нам ключем, в этом случае перезаписываем
        if(keyToInsert.equals(this.key)) {
            this.key = keyToInsert;
            this.value = valueToStore;
            isDeleted = false;
            return;
        }
        //2 - это последняя ячейка в цепочке, создаем новую
        if (isNull(nextBucket)) {
            nextBucket = new Bucket(keyToInsert, valueToStore);
            return;
        }
        nextBucket.insert(keyToInsert, valueToStore);
    }
    public V get(K keyToFind) {
        if(isDeleted) {
            return null;
        }
        if(keyToFind.equals(key)) {
            return value;
        }
        if(nonNull(nextBucket)) {
            return nextBucket.get(keyToFind);
        }
        return null;
    }

    public boolean remove(K keyToRemove) {
        if(keyToRemove.equals(this.key)) {
            isDeleted = true;
            return true;
        }else if(nonNull(nextBucket)){
            return nextBucket.remove(keyToRemove);
        }
        return false;
    }

    public Bucket getNextBucket() {
        return nextBucket;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }
}
