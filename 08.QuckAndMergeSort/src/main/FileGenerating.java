package main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class FileGenerating {

    public static void generateFileWithRandomShorts(int amountOfNumbers, String fileName) throws IOException {
        File file = new File(fileName);
        try (DataOutputStream fos = new DataOutputStream(new FileOutputStream(file))) {
            for(int i = 0; i <= amountOfNumbers; i++) {
                fos.writeShort((short) (Math.random()* Short.MAX_VALUE));
            }
        }
    }


}
