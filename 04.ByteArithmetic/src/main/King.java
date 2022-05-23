package main;

public class King {

    public long getMoves(long n) {
        long position = 1L << n;
        long forbiddenLeft = position & 0xfefefefefefefefeL;
        long forbiddenRight = position & 0x7f7f7f7f7f7f7f7fL;

        long result =
                (forbiddenLeft << 7) | (position << 8) | (forbiddenRight << 9) |
                (forbiddenLeft >> 1)  |                  (forbiddenRight << 1) |
                (forbiddenLeft >> 9)  | (position >> 8)| (forbiddenRight >> 7);
        return result;
    }

}
