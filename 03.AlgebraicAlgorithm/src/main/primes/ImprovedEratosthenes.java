package main.primes;

public class ImprovedEratosthenes {

    public static void main(String[] args) {
        System.out.println(countPrimeNumbers(10000));
    }
    static int counter;

    public static long countPrimeNumbers(int n) {
        int p = 2;
        counter = 1;
        int[] arrayOfAllPrimes = new int[n/2];
        arrayOfAllPrimes[0] = 2;
        int[] arrayOfAllNumbers = new int[n];
        //генерим массив
        for (int j = 3; j < n; j++) {
            arrayOfAllNumbers[j] = j;
        }

        for (; p< n; p++) {
            for (int i = 0; i < counter; i++) {
                int j = p * arrayOfAllPrimes[i];
                //защита от arrayOutOfBound
                if(j >= n ) {
                    continue;
                }
                arrayOfAllNumbers[j] = 0;
            }
            //число не изменилось за прошлые итерации - значит простое
            if (arrayOfAllNumbers[p] == p) {
                if(p*p < n) {
                    arrayOfAllNumbers[p*p] = 0;
                }
                arrayOfAllPrimes[counter] = p;
                counter++;
            }
        }
        return counter;
    }

}
