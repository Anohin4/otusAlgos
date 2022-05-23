package main;

public class Bishop {
    public long getMoves(int n) {
        int down = n / 8;
        int up = 7 - down;
        int right = 7 - n % 8;

        int left = 7 - right;
        long position = 1L << n;
        long result = 0;

        for (int i = 1; i <=7; i++) {
            if (up >= i) {
                if (right >= i) {
                    result = result | position << (9 * i);
                }
                if (left >= i) {
                    result = result | position << (7 * i);
                }
            }

            if (down >= i) {
                if (right >= i) {
                    result = result | position >> (7 * i);
                }
                if (left >= i) {
                    result = result | position >> (9 * i);
                }
            }

        }
        result = result & ~position;
        return result;
    }

}
