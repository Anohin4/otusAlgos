package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class FileGenerating {

    public void generateFileWithRandomShorts() {
        File file = new File("input.txt");


        try (FileOutputStream fos = new FileOutputStream(file)) {
            for(int i = 0; i <= 65535; i++) {
                fos.write((short) new Random().nextInt(Short.MAX_VALUE));
            }

            System.out.println("Successfully written data to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
