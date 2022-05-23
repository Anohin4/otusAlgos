package main;

public class Knight {

    public  long getMoves(int n) {
        long position = 1L << n;
        long forbiddenRight = 0x3f3f3f3f3f3f3f3fL;
        long forbiddenRightEdge = 0x7f7f7f7f7f7f7f7fL;
        long forbiddenLeftEdge = 0xfefefefefefefefeL;
        long forbiddenLeft =  0xfcfcfcfcfcfcfcfcL;

        return forbiddenRightEdge &(position << 15 | position >> 17) |
         forbiddenLeftEdge & (position << 17 | position >> 15) |
       forbiddenRight & (position << 6  | position >> 10) |
                forbiddenLeft & (position << 10 | position >> 6);
    }

}
