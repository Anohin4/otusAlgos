package main.primes;

public class ImprovedEratosthenes {

    public static void main(String[] args) {
        System.out.println(countPrimeNumbers(10000));
    }

    public static long countPrimeNumbers(int n) {
        int counter = 0;
        int[] arrayOfAllPrimes = new int[n/2];
        int[] arrayOfAllNumbers = new int[n];

        for (int p = 2; p< n; p++) {
            //число не изменилось за прошлые итерации - значит простое
            if (arrayOfAllNumbers[p] == 0) {
                //вычеркиваем квадрат числа
                if(p*p < n) {
                    arrayOfAllNumbers[p*p] = 1;
                }
                arrayOfAllPrimes[counter] = p;
                counter++;
            }

            //проставляем 1 в те числа, которые точно не простые
            for (int i = 0; i < counter; i++) {
                int j = p * arrayOfAllPrimes[i];
                //защита от arrayOutOfBound
                if(j >= n ) {
                    continue;
                }
                arrayOfAllNumbers[j] = 1;
            }

        }
        return counter;
    }

}
