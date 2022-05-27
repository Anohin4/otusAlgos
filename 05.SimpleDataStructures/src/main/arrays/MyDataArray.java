package main.arrays;

public interface MyDataArray<T> {

    int size();

    void add(T item);

    T get(int position);

    void add(T item, int position);

    T remove(int position);

}
