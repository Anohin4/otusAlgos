package main;

public class King {

    public static void main(String[] args) {
        System.out.println(getMoves(0x8000000000000000L));
    }
    public static long getMoves(long n) {
        long forbiddenLeft = n & 0xfefefefefefefefeL;
        long forbiddenRight = n & 0x7f7f7f7f7f7f7f7fL;

        long result =
                (forbiddenLeft  << 7) | (n << 8) | (forbiddenRight << 9) |
                (forbiddenLeft >> 1)  |                   (forbiddenRight << 1) |
                (forbiddenLeft >> 9 ) | (n >> 8) | (forbiddenRight >> 7);
        if (result < 0) {
           result =  result >>> 2;

        }
        return result;
    }
    public static long makeMove(long number, int howFar, boolean isDirectionLeft) {
        long result;
        if (isDirectionLeft) {
            result = number >> howFar;
        } else {
            result = number << howFar;
        }
        return number > 0 ? number : 0;
    }

}
