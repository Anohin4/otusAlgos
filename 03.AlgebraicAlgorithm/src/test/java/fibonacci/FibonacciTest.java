package test.java.fibonacci;

import static test.java.primes.TestPrimes.runTest;
import static test.java.fibonacci.TestFiboUtil.runFibonacciTest;

import main.fibonachi.IterationFibonacci;
import main.fibonachi.MatrixFibonacci;

public class FibonacciTest {

    public static void main(String[] args) {
        int amountOfTests = 12;
        String catalogName = "Fibo";


        IterationFibonacci iterationFibonacci = new IterationFibonacci();
        MatrixFibonacci matrix = new MatrixFibonacci();

        System.out.println("-----------Iteration Fibonacci tests -------------");
        runFibonacciTest(catalogName, amountOfTests, iterationFibonacci::getFibonacciNumber);
        System.out.println("-----------Fibonacci with matrix tests -------------");
        runFibonacciTest(catalogName, amountOfTests, 30000,matrix::getFibonacciNumber);

    }

}
