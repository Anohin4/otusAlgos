package main.fibonachi;
/*
Матрица поктороая выглядит так
F3 F2
F1 F0
 */
public class Matrix {
    private long f3;
    private long f2;
    private long f1;
    private long f0;

    public Matrix(long f3, long f2, long f1, long f0) {
        this.f3 = f3;
        this.f2 = f2;
        this.f1 = f1;
        this.f0 = f0;
    }

    public Matrix (Matrix matrix) {
        this.f3 = matrix.getF3();
        this.f2 = matrix.getF2();
        this.f1 = matrix.getF1();
        this.f0 = matrix.getF0();
    }

    public long getF3() {
        return f3;
    }

    public void setF3(long f3) {
        this.f3 = f3;
    }

    public long getF2() {
        return f2;
    }

    public void setF2(long f2) {
        this.f2 = f2;
    }

    public long getF1() {
        return f1;
    }

    public void setF1(long f1) {
        this.f1 = f1;
    }

    public long getF0() {
        return f0;
    }

    public void setF0(long f0) {
        this.f0 = f0;
    }
}
