package main.queue;

public interface MyQueue<T> {

    int size();
    T dequeue();
    void enqueue(T item);
    void enqueue(T item, int priority);

}
