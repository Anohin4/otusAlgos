package main.arrays;

import java.util.Arrays;
import main.arrays.MyDataArray;

public class VectorArray<T> implements MyDataArray<T> {

    Object[] array;
    int currentSize;
    int vector;

    public VectorArray() {
        this.array = new Object[0];
        this.currentSize = 0;
        this.vector = 8;
    }

    public VectorArray(int vector) {
        this.array = new Object[0];
        this.currentSize = 0;
        this.vector = vector;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        if(currentSize == array.length) {
            resize();
        }
        array[currentSize] = item;
        currentSize++;
    }

    private void resize() {
        Object[] newArray = new Object[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, size());
        this.array = newArray;

    }
    @Override
    public void add(T item, int position) {
        if(currentSize == array.length) {
            resize();
        }
        int needToCopy = array.length - position - 1;
        Object[] newArray = new Object[needToCopy];
        System.arraycopy(array, position, newArray, 0, needToCopy);
        array[position] = item;
        System.arraycopy(newArray, 0, array, position + 1, needToCopy);
        currentSize++;
    }


    @Override
    @SuppressWarnings("unchecked cast")
    public T get(int position) {
        return (T) array[position];
    }

    @Override
    @SuppressWarnings("unchecked cast")
    public T remove(int position) {
        T removedValue = (T) array[position];
        for(int i = position; i<currentSize - 1; i++) {
            array[i] = array[i + 1];
        }
        array[currentSize - 1] = null;
        currentSize--;
        return removedValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
