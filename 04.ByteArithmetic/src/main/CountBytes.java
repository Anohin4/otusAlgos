package main;

public class CountBytes {
    private static int[] cache;
    static {
         cache = new int[256];
        for(int i = 0; i<256; i++) {
            cache[i] = popcnt(i);
        }
    }

    public static void main(String[] args) {
        System.out.println(popcnt(1688987299217408L));
        System.out.println(iterationPopcnt(1688987299217408L));
        System.out.println(cashedPopcnt(1688987299217408L));
    }

    public static int popcnt(long mask) {
        int counter = 0;
        long a = mask;
        while (a > 0) {
            a &= a-1;
            counter++;
        }
        return counter;
    }

    public static int cashedPopcnt(long mask) {
        long tempValue = mask;
        int counter = 0;
        while(tempValue > 0) {
           counter += cache[(int)(tempValue & 255)];
           tempValue = tempValue >> 8;
        }
        return counter;
    }

    public static int iterationPopcnt(long mask) {
        int counter = 0;
        long a = mask;
        while (a > 0) {
            if((a & 1) == 0) {
                counter++;
            }

        }
        return counter;
    }



}
