package test.java.fibonacci;

import static test.java.primes.TestPrimesTemplate.runTest;
import static test.java.fibonacci.TestFiboTemplate.runFibonacciTest;

import main.fibonachi.IterationFibonacci;
import main.fibonachi.MatrixFibonacci;
import main.fibonachi.RecursiveFibonacci;

public class FibonacciTest {

    public static void main(String[] args) {
        int amountOfTests = 12;
        String catalogName = "Fibo";

        IterationFibonacci iterationFibonacci = new IterationFibonacci();
        MatrixFibonacci matrix = new MatrixFibonacci();
        RecursiveFibonacci recursiveFibonacci = new RecursiveFibonacci();

        System.out.println("-----------Iteration Fibonacci tests -------------");
        runFibonacciTest(catalogName, amountOfTests, 30000,iterationFibonacci::getFibonacciNumber);
        System.out.println("-----------Recursive Fibonacci tests -------------");
        runFibonacciTest(catalogName, amountOfTests, 30000,recursiveFibonacci::getFibonacciNumber);
        System.out.println("-----------Fibonacci with matrix tests -------------");
        runFibonacciTest(catalogName, amountOfTests, 30000,matrix::getFibonacciNumber);

    }

}
