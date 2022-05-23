package main;

public class Rook {

    public long getMoves(int n) {
        int down = n / 8;
        int up = 7 - down;
        int right = 7 - n % 8;

        int left = 7 - right;
        long position = 1L << n;
        long result = 0;

        for (int i = 1; i <= 7; i++) {
            if (right >= i) {
                result = result | position << i;
            }
            if (left >= i) {
                result = result | position >> i;
            }
            if (up >= i) {
                result = result | position << (8 * i);
            }
            if (down >= i) {
                result = result | position >> (8 * i);
            }
        }
        return result;
    }
}
