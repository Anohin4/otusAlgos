package main.arrays;

public class FactorArrayForQueue<T> extends FactorArray<T> {

    int priority;

    public int getPriority() {
        return priority;
    }

    public FactorArrayForQueue(int priority) {
        super(2);
        this.priority = priority;
    }
}
