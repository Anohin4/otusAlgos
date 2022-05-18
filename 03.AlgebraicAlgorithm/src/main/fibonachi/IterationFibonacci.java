package main.fibonachi;

import java.math.BigInteger;

public class IterationFibonacci {

    public String getFibonacciNumber(int n) {
        BigInteger f1 = BigInteger.ONE;
        BigInteger f2 = BigInteger.ONE;;
        BigInteger result = BigInteger.ZERO;
        if (n == 0) {
            return BigInteger.ZERO.toString();
        }
        if (n == 1 || n == 2) {
            return BigInteger.ONE.toString();
        }
        for (n -= 2; n > 0; n--) {
            result = f2.add(f1);
            f1 = f2;
            f2 = result;
        }
        return result.toString();

    }

}
