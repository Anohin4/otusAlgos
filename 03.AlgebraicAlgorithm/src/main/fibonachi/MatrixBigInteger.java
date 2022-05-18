package main.fibonachi;

import java.math.BigInteger;

/*
Матрица поктороая выглядит так
F3 F2
F1 F0
 */
public class MatrixBigInteger {

    private BigInteger f3;
    private BigInteger f2;
    private BigInteger f1;
    private BigInteger f0;

    public MatrixBigInteger(BigInteger f3, BigInteger f2, BigInteger f1, BigInteger f0) {
        this.f3 = f3;
        this.f2 = f2;
        this.f1 = f1;
        this.f0 = f0;
    }

    public MatrixBigInteger(MatrixBigInteger matrix) {
        this.f3 = matrix.getF3();
        this.f2 = matrix.getF2();
        this.f1 = matrix.getF1();
        this.f0 = matrix.getF0();
    }

    public BigInteger getF3() {
        return f3;
    }

    public void setF3(BigInteger f3) {
        this.f3 = f3;
    }

    public BigInteger getF2() {
        return f2;
    }

    public void setF2(BigInteger f2) {
        this.f2 = f2;
    }

    public BigInteger getF1() {
        return f1;
    }

    public void setF1(BigInteger f1) {
        this.f1 = f1;
    }

    public BigInteger getF0() {
        return f0;
    }

    public void setF0(BigInteger f0) {
        this.f0 = f0;
    }
}
