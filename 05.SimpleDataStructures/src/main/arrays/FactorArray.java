package main.arrays;

import java.util.Arrays;

public class FactorArray<T> implements MyDataArray<T> {

    private Object[] array;
    private int currentSize;
    private int factor;

    public FactorArray() {
        this.array = new Object[0];
        this.currentSize = 0;
        this.factor = 2;
    }

    public FactorArray(int factor) {
        this.array = new Object[factor];
        this.currentSize = 0;
        this.factor = factor;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void add(T item) {
        if (currentSize == array.length) {
            resize();
        }
        array[currentSize] = item;
        currentSize++;
    }

    private void resize() {
        Object[] newArray = new Object[array.length * factor];
        if(array.length == 0) {
            newArray = new Object[1];
        }
        System.arraycopy(array, 0, newArray, 0, size());
        this.array = newArray;

    }

    @Override
    public void add(T item, int position) {
        if (currentSize == array.length) {
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

        //Если массив можно уменьшить после удаления- уменьшаем. Если нет - просто сдвигаем элементы
        if(currentSize - 1< array.length/factor && currentSize > 2) {
            Object[] newArray = new Object[array.length/2];
            System.arraycopy(array, 0, newArray,0, position);
            System.arraycopy(array, position + 1, newArray, position, currentSize - position - 1);
            this.array = newArray;
        } else {
            if (currentSize - 1 - position > 0)
                System.arraycopy(array, position + 1, array, position, currentSize - 1 - position);
            array[currentSize - 1] = null;
        }
        currentSize--;
        return removedValue;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
