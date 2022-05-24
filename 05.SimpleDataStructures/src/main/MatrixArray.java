package main;

public class MatrixArray<T> implements MyDataStructure<T> {

    private MyDataStructure<MyDataStructure<T>> array;
    int currentSize;
    int factor;

    public MatrixArray(int factor) {
        array = new SingleArray<>();
        this.factor = factor;
        this.currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void add(T item) {
        if (currentSize == factor * array.size()) {
            array.add(new VectorArray<>(factor));
        }
        array.get(currentSize / factor).add(item);
    }

    @Override
    public T get(int position) {
        return array.get(position / factor).get(position % factor);
    }

    @Override
    public void add(T item, int position) {
        int numberOfArray = position /currentSize;
        MyDataStructure<T> initialArray = array.get(numberOfArray);
        initialArray.add(item, position % factor);
        currentSize++;
        if (currentSize == factor * array.size()) {
            array.add(new VectorArray<>(factor));
        }

        while (initialArray.size() > factor) {
            T temp = initialArray.get(factor);
            initialArray.remove(factor);
            numberOfArray++;
            initialArray = array.get(numberOfArray);
            initialArray.add(temp, 0);
        }

    }

    @Override
    public T remove(int position) {
        int numberOfArray = position /currentSize;
        MyDataStructure<T> initialArray = array.get(numberOfArray);
        return null;
    }
}
