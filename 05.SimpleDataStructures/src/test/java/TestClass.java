package test.java;

import static test.java.TestTemplateForDataStructure.runTest;

import main.arrays.ArrayListWrapper;
import main.arrays.FactorArray;
import main.arrays.MatrixArray;
import main.arrays.SingleArray;
import main.arrays.SpaceArray;
import main.arrays.VectorArray;

public class TestClass {

    public static void main(String[] args) {

        SingleArray<Integer> singleArray = new SingleArray<>();
        VectorArray<Integer> vectorArray = new VectorArray<>(8);
        FactorArray<Integer> factorArray = new FactorArray<>(2);
        MatrixArray<Integer> matrixArray = new MatrixArray<>(8);
        SpaceArray<Integer> spaceArray = new SpaceArray<>();
        ArrayListWrapper<Integer> arrayListWrapper = new ArrayListWrapper<>();

//        for( int i = 0; i < 10; i++) {
//            factorArray.add(i);
//        }
//        for( int i = 0; i < 10; i++) {
//            factorArray.remove(0);
//        }
        System.out.println("---------adding elements-------");
        runTest(SingleArray.class, () -> singleArray.add(0));
        runTest(VectorArray.class, () -> vectorArray.add(0));
        runTest(FactorArray.class, () -> factorArray.add(0));
        runTest(MatrixArray.class, () -> matrixArray.add(0));
        runTest(SpaceArray.class, () -> spaceArray.add(0));
        runTest(ArrayListWrapper.class, () -> arrayListWrapper.add(0));

        System.out.println("---------removing element from the beginning-------");
        runTest(SingleArray.class, () -> singleArray.remove(0));
        runTest(VectorArray.class, () -> vectorArray.remove(0));
        runTest(FactorArray.class, () -> factorArray.remove(0));
        runTest(MatrixArray.class, () -> matrixArray.remove(0));
        runTest(SpaceArray.class, () -> spaceArray.remove(0));
        runTest(ArrayListWrapper.class, () -> arrayListWrapper.remove(0));
        System.out.println("---------adding element to the beginning-------");
        runTest(SingleArray.class, () -> singleArray.add(0, 0));
        runTest(VectorArray.class, () -> vectorArray.add(0, 0));
        runTest(FactorArray.class, () -> factorArray.add(0, 0));
        runTest(MatrixArray.class, () -> matrixArray.add(0, 0));
        runTest(SpaceArray.class, () -> spaceArray.add(0, 0));
        runTest(ArrayListWrapper.class, () -> arrayListWrapper.add(0, 0));


    }

}
