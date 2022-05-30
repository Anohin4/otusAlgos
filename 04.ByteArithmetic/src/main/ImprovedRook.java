package main;

public class ImprovedRook {

    public long getMoves(int n) {
        long position = 1L << n;
        int axisX = (n/8) * 8;
        int axisY = n % 8;

        return (255L << axisX | 72340172838076673L << axisY) & ~position;
    }
}
