package test.java.power;

import static test.java.power.TestPowTemplate.runPowerTest;

import main.power.IterationPow;
import main.power.PowerAdvanced;
import main.power.PowerWithMultiplication;

public class PowerTest {

    public static void main(String[] args) {
        IterationPow iterationPow = new IterationPow();
        PowerAdvanced advanced = new PowerAdvanced();
        PowerWithMultiplication multiplication = new PowerWithMultiplication();
        String catalog = "Power";
        int amountOfTests = 9;

        System.out.println("-----------Iteration tests -------------");
        runPowerTest(catalog, amountOfTests, iterationPow::power);
        System.out.println("-----------Advanced tests -------------");
        runPowerTest(catalog, amountOfTests, advanced::power);
        System.out.println("-----------Multiplication tests -------------");
        runPowerTest(catalog, amountOfTests, multiplication::power);

    }

}
