package main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileGenerating {

    public static void generateFileWithRandomInt(int amountOfNumbers, String fileName) throws IOException {
        File file = new File(fileName);
        try (DataOutputStream fos = new DataOutputStream(new FileOutputStream(file))) {
            for(int i = 0; i <= amountOfNumbers; i++) {
                fos.writeInt((int) (Math.random()* 65000));
            }
        }
    }
}
