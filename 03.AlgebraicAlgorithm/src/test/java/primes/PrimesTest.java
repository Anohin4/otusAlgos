package test.java.primes;

import static test.java.primes.TestPrimesTemplate.runTest;

import main.primes.LinearEratosthenes;
import main.primes.PrimesWithImprovements;
import main.primes.PrimesWithIteration;
import main.primes.SieveOfEratosthenes;

public class PrimesTest {

    public static void main(String[] args) {
        int amountOfTests = 15;
        String catalogName = "Primes";

        PrimesWithIteration iteration = new PrimesWithIteration();
        PrimesWithImprovements improvedPrimes = new PrimesWithImprovements();
        SieveOfEratosthenes eratosthenes = new SieveOfEratosthenes();
        LinearEratosthenes linearEratosthenes = new LinearEratosthenes();
        System.out.println("-----------Iteration prime counting tests -------------");
        runTest(catalogName, amountOfTests, 30000, iteration::countPrimeNumbers);
        System.out.println("-----------Iteration prime with optimisation counting tests -------------");
        runTest(catalogName, amountOfTests, 30000, improvedPrimes::countPrimeNumbers);
        System.out.println("-----------Sieve of Eratosthenes counting tests -------------");
        runTest(catalogName, amountOfTests,30000, eratosthenes::countPrimeNumbers);
        System.out.println("-----------Linear sieve of Eratosthenes counting tests -------------");
        runTest(catalogName, amountOfTests, 30000, linearEratosthenes::countPrimeNumbers);
    }
}
