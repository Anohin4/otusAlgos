package main.queue;

public interface MyQueue<T> {

    int size();
    T pool();
    void enqueue(T item);
    void enqueue(T item, int priority);

}
