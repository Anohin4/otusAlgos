package main.fibonachi;

import java.math.BigInteger;

public class RecursiveFibonacci {
    public String getFibonacciNumber(int n) {
        return calculateFibonacciNumber(n).toString();
    }

    public BigInteger calculateFibonacciNumber(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1 || n == 2) {
            return BigInteger.ONE;
        }
        return calculateFibonacciNumber(n - 1).add(calculateFibonacciNumber(n - 2));
    }

}
