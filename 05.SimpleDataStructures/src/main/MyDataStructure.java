package main;

public interface MyDataStructure<T> {
    int size();
    void add(T item);
    T get(int position);
    void add(T item, int position);
    T remove(int position);

}
