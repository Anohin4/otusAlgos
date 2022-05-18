package main.fibonachi;

public class MatrixFibonacci {

    public long getFibonacciNumber(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        Matrix result = powFibonacciMatrix(n - 1);

        return result.getF3();
    }

    private Matrix powFibonacciMatrix(int pow) {
        Matrix result = new Matrix(1, 1, 1, 0);

        for (int n = pow; n > 1; n--) {
            Matrix tempMatrix = new Matrix(result);
            tempMatrix.setF3(result.getF3() + result.getF2());
            tempMatrix.setF2(result.getF3());
            tempMatrix.setF1(result.getF1() + result.getF0());
            tempMatrix.setF0(result.getF1());
            result = tempMatrix;
        }
        return result;

    }

}
