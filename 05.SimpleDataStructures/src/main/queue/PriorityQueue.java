package main.queue;

import main.arrays.FactorArrayForQueue;
import main.arrays.MyDataArray;
import main.arrays.SingleArray;

public class PriorityQueue<T> implements MyQueue<T> {

    private final MyDataArray<FactorArrayForQueue<T>> arrayOfArrays;
    private final int factor;
    private int size;

    public PriorityQueue(int factor) {
        this.factor = factor;
        this.arrayOfArrays = new SingleArray<>();
        this.arrayOfArrays.add(new FactorArrayForQueue<>(factor));
        this.size = 0;
    }

    public PriorityQueue() {
        this.factor = 2;
        this.arrayOfArrays = new SingleArray<>();
        this.arrayOfArrays.add(new FactorArrayForQueue<>(factor));
        this.size = 0;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            return null;
        }
        T result = arrayOfArrays.get(arrayOfArrays.size() - 1).get(0);
        arrayOfArrays.get(arrayOfArrays.size() - 1).remove(0);
        //удаляем пустой последний массив
        if (arrayOfArrays.size() != 1
                && arrayOfArrays.get(arrayOfArrays.size() - 1).size() == 0) {
            arrayOfArrays.remove(arrayOfArrays.size() - 1);
        }
        size--;
        return result;
    }

    @Override
    public void enqueue(T item) {
        arrayOfArrays.get(0).add(item);
        size++;
    }

    @Override
    public void enqueue(T item, int priority) {
        int currentMaxPriority = arrayOfArrays.get(arrayOfArrays.size() - 1).getPriority();
        if (priority > currentMaxPriority) {
            FactorArrayForQueue<T> newQueue = new FactorArrayForQueue<>(priority);
            newQueue.add(item);
            arrayOfArrays.add(newQueue);
        } else if (priority == currentMaxPriority) {
            arrayOfArrays.get(arrayOfArrays.size() - 1).add(item);
        } else {
            int position = findPosition(priority);
            if (arrayOfArrays.get(position).getPriority() == priority) {
                arrayOfArrays.get(position).add(item);
            } else {
                FactorArrayForQueue<T> newQueue = new FactorArrayForQueue<>(priority);
                newQueue.add(item);
                arrayOfArrays.add(newQueue, position);
            }
        }
        size++;
    }

    public int findPosition(int priority) {
        int min = 0;
        int max = arrayOfArrays.size() - 1;
        int result = 1;

        while (min <= max) {
            int mid = min + ((max - min) / 2);
            int midPriority = arrayOfArrays.get(mid).getPriority();
            if (midPriority > priority) {
                max = mid - 1;
                result = mid;
            } else if (midPriority < priority) {
                min = mid + 1;
            } else if (midPriority == priority) {
                result = mid;
                break;
            }
        }
        return result;


    }
}
