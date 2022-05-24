package main;

import java.util.Arrays;

public class SingleArray<T> implements MyDataStructure<T> {
    Object[] array;

    public SingleArray() {
        this.array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        Object[] newArray = new Object[array.length + 1];
        for(int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = item;
        array = newArray;
    }

    @Override
    public void add(T item, int position) {
        Object[] newArray = new Object[array.length + 1];
        System.arraycopy(array, 0, newArray,0, position);
        newArray[position] = item;
        System.arraycopy(array, position, newArray, position + 1, array.length - position);
        array = newArray;
    }
    private void resize() {

    }

    @Override
    @SuppressWarnings("unchecked cast")
    public T get(int position) {
        return (T) array[position];
    }

    @Override
    @SuppressWarnings("unchecked cast")
    public T remove(int position) {
        Object[] newArray = new Object[array.length - 1];
        System.arraycopy(array, 0, newArray,0, position);
        System.arraycopy(array, position + 1, newArray, position, array.length - position - 1);
        T removedValue = (T) array[position];
        array = newArray;
        return removedValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
