package main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class FileGenerating {

    public void generateFileWithRandomShorts() {
        File file = new File("input.txt");


        try (DataOutputStream fos = new DataOutputStream(new FileOutputStream(file))) {
            for(int i = 0; i <= 655; i++) {
                fos.writeShort(new Random().nextInt(Short.MAX_VALUE));
            }

            System.out.println("Successfully written data to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
