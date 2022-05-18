package main.fibonachi;

import java.math.BigInteger;

public class MatrixFibonacci {


    public String getFibonacciNumber(int n) {
        if (n == 0) {
            return BigInteger.ZERO.toString();
        }
        if (n == 1 || n == 2) {
            return BigInteger.ONE.toString();
        }
        MatrixBigInteger result = powFibonacciMatrix(n - 1);

        return result.getF3().toString();
    }

    private static MatrixBigInteger powFibonacciMatrix(int pow) {
        MatrixBigInteger result = new MatrixBigInteger(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO);

        for (int n = pow; n > 1; n--) {
            MatrixBigInteger tempMatrix = new MatrixBigInteger(result);
            tempMatrix.setF3(result.getF3().add(result.getF2()));
            tempMatrix.setF2(result.getF3());
            tempMatrix.setF1(result.getF1().add(result.getF0()));
            tempMatrix.setF0(result.getF1());
            result = tempMatrix;
        }
        return result;

    }

}
