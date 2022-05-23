package main;

public class Rook {

    public long getMoves(int n) {
        int down = n / 8;
        int up = 7 - down;
        int right = 7 - n % 8;;
        int left = 7 - right;
        long position = 1L << n;
        long result = 0;

        for (int i = 1; i <= 7; i ++) {
            result = result | position << 8 * up;
            result = result | position >> 8 * down;
            result = result | position <<  right;
            result = result | position >> left;
            up--;
            down--;
            left--;
            right--;
        }
        result = result & ~position;
        return result;
    }
}
