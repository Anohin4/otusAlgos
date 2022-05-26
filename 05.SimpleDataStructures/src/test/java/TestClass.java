package test.java;

import static test.java.TestTemplateForDataStructure.runIterationAddingTest;
import static test.java.TestTemplateForDataStructure.runRunnableTest;

import main.arrays.ArrayListWrapper;
import main.arrays.FactorArray;
import main.arrays.MatrixArray;
import main.arrays.SingleArray;
import main.arrays.VectorArray;

public class TestClass {

    public static void main(String[] args) {
        int numberOfElems = 100000;
        System.out.println("---------adding elements-------");
        SingleArray<Integer> singleArray = new SingleArray<>();
        VectorArray<Integer> vectorArray = new VectorArray<>(8);
        FactorArray<Integer> factorArray = new FactorArray<>(2);
        MatrixArray<Integer> matrixArrayArray = new MatrixArray<>(8);
        ArrayListWrapper<Integer> arrayListWrapper = new ArrayListWrapper<>();
        runIterationAddingTest(singleArray,numberOfElems);
        runIterationAddingTest(vectorArray, numberOfElems);
        runIterationAddingTest(factorArray, numberOfElems);
        runIterationAddingTest(matrixArrayArray, numberOfElems);
        runIterationAddingTest(arrayListWrapper, numberOfElems);
        System.out.println("---------adding element in the middle-------");
        runRunnableTest(singleArray, () -> singleArray.add(0, 0));
        runRunnableTest(vectorArray, () -> vectorArray.add(0, 0));
        runRunnableTest(factorArray, () -> factorArray.add(0, 0));
        runRunnableTest(matrixArrayArray, () -> matrixArrayArray.add(0, 0));
        runRunnableTest(arrayListWrapper, () -> arrayListWrapper.add(0, 0));
        System.out.println("---------removing element from the middle-------");
        runRunnableTest(singleArray, () -> singleArray.remove( 0));
        runRunnableTest(vectorArray, () -> vectorArray.remove(0));
        runRunnableTest(factorArray, () -> factorArray.remove(0));
        runRunnableTest(matrixArrayArray, () -> matrixArrayArray.remove( 0));
        runRunnableTest(arrayListWrapper, () -> arrayListWrapper.remove( 0));

    }

}
