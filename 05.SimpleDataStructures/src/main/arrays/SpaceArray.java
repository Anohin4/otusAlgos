package main.arrays;

public class SpaceArray<T> implements MyDataArray<T> {

    private MyDataArray<MyDataArray<T>> arrayOfArrays;
    private int currentSize;
    private int factor;

    public SpaceArray() {
        this.factor = 32;
        this.arrayOfArrays = new FactorArray<>(factor);
        this.currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void add(T item) {
        if (currentSize % factor == 0) {
            arrayOfArrays.add(new FactorArray<>(factor));
        }
        int array = currentSize / factor;
        arrayOfArrays.get(array).add(item);
        currentSize++;
    }

    @Override
    public T get(int position) {
        return arrayOfArrays.get(currentSize / factor).get(currentSize % factor);
    }

    @Override
    public void add(T item, int position) {
        if (currentSize % factor == 0) {
            arrayOfArrays.add(new FactorArray<>(factor));
        }
        int numberOfArray = position / factor;
        MyDataArray<T> workingArray = arrayOfArrays.get(numberOfArray);
        //берем нужный нам массив и добавляем в него значение в нужную позицию
        if (workingArray.size() != factor) {
            //если массив не полный - просто добавляем со сдвигом, не трогая остальные и заканчиваем на этом
            workingArray.add(item, position % factor);
            currentSize++;
            return;
        }
        //если массив полный - удаляем последнее значение и начинаем итерироваться по массиву пока не найдем не полный
        T tempFromPreviousArray = workingArray.remove(workingArray.size() - 1);
        workingArray.add(item, position % factor);
        numberOfArray++;

        while (true) {
            workingArray = arrayOfArrays.get(numberOfArray);
            if (workingArray.size() != factor) {
                //если в массиве есть место - просто добавляем со сдвигом, не трогая остальные и заканчиваем
                workingArray.add(tempFromPreviousArray, 0);
                break;
            } else {
                T removed = workingArray.remove(workingArray.size() - 1);
                workingArray.add(tempFromPreviousArray, 0);
                tempFromPreviousArray = removed;
            }
            numberOfArray++;
        }
        currentSize++;
    }

    @Override
    public T remove(int position) {
        T removedElement = arrayOfArrays.get(position / factor).remove(position % factor);
        currentSize--;
        //проверка, что массив не пустой
        //пустой - удаляем
        if (arrayOfArrays.get(position / factor).size() == 0 && arrayOfArrays.size() != 1) {
            arrayOfArrays.remove(position/factor);
        }
        return removedElement;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayOfArrays.size(); i++) {
            sb.append(arrayOfArrays.get(i));
        }
        return sb + "\n current size " + currentSize;
    }
}
