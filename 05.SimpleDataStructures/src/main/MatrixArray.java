package main;

import static java.util.Objects.nonNull;

public class MatrixArray<T> implements MyDataStructure<T> {

    private MyDataStructure<MyDataStructure<T>> arrayOfArrays;
    int currentSize;
    int factor;

    public MatrixArray(int factor) {
        arrayOfArrays = new SingleArray<>();
        this.factor = factor;
        this.currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void add(T item) {
        if (currentSize == factor * arrayOfArrays.size()) {
            arrayOfArrays.add(new VectorArray<>(factor));
        }
        arrayOfArrays.get(currentSize / factor).add(item);
        currentSize++;
    }

    @Override
    public T get(int position) {
        return arrayOfArrays.get(position / factor).get(position % factor);
    }

    @Override
    public void add(T item, int position) {
        if (currentSize == factor * arrayOfArrays.size() ) {
            arrayOfArrays.add(new VectorArray<>(factor));
        }

        int numberOfArray = position /factor;
        MyDataStructure<T> workingArray = arrayOfArrays.get(numberOfArray);
        if(workingArray.size() != factor) {
            //если массив не полный - он последний, просто добавляем
            workingArray.add(item, position % factor);
        } else {
            // если полный - в любом случае не последний
            //вставляем данные в первый массив.
            T tempFromPreviousArray = workingArray.remove(workingArray.size()- 1);
            workingArray.add(item, position % factor);
            numberOfArray++;

            //сдвигаем все значения в остальных массивах
            for (int i = numberOfArray; i < arrayOfArrays.size() - 1; i++) {
                workingArray = arrayOfArrays.get(i);
                T removed = workingArray.remove(workingArray.size() - 1);
                workingArray.add(tempFromPreviousArray, 0);
                numberOfArray++;
                tempFromPreviousArray = removed;
            }

            //в последний массив добавляем значение в начало
            arrayOfArrays.get(arrayOfArrays.size() - 1)
                    .add(tempFromPreviousArray, 0);
        }
        currentSize++;
    }

    @Override
    public T remove(int position) {
        int numberOfArray = position /factor;
        MyDataStructure<T> workingArray = arrayOfArrays.get(numberOfArray);
        T removedElement = workingArray.remove(position%factor);
        currentSize --;
        //дальнейшие действия актуальны только если данный массив не последний
        if(numberOfArray < arrayOfArrays.size() - 1) {
            //добавляем на последнее место массива первый элемент следующего массива
            workingArray.add(arrayOfArrays.get(numberOfArray + 1).get(0));
            numberOfArray++;
            //делаем сдвиг во всех остальных массивах кроме последнего
            for (int i = numberOfArray; i < arrayOfArrays.size() - 1; i++) {
                workingArray = arrayOfArrays.get(i);
                workingArray.remove(0);
                workingArray.add(arrayOfArrays.get(i + 1).get(0));
            }
            //для последнего массива просто удаляем первый элемент
            arrayOfArrays.get(arrayOfArrays.size() - 1).remove(0);
        }

        //проверка, что последний элемент не пустой
        //пустой - удаляем
        if (!nonNull(arrayOfArrays.get(arrayOfArrays.size() - 1).get(0))) {
            arrayOfArrays.remove(arrayOfArrays.size() - 1);
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
