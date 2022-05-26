package main.arrays;

import java.util.ArrayList;
import java.util.List;

public class ArrayListWrapper<T> implements MyDataArray<T>{
    private List<T> arrayList;

    public ArrayListWrapper() {
        this.arrayList = new ArrayList<>();
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public void add(T item) {
        arrayList.add(item);
    }

    @Override
    public T get(int position) {
        return arrayList.get(position);
    }

    @Override
    public void add(T item, int position) {
        arrayList.add(position, item);
    }

    @Override
    public T remove(int position) {
        return arrayList.remove(position);
    }
}
