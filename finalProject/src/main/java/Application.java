import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Application {

    public static void main(String[] args) throws IOException {

        try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File("finalProject/src/main/resources/hashSeeds.txt")))) {
            int i = 0;
            while (i<40) {
                writer.write(String.valueOf(Math.abs(new Random().nextInt())));
                writer.write(System.getProperty("line.separator"));
                i++;
            }
        }
//        MemTable memTable = new MemTable("testTable");
//        System.out.println(memTable.getSize());
//        memTable.addValue(OperationEnum.INSERT, "123", "321");
//        memTable.addValue(OperationEnum.INSERT, "1243", "321");
//        memTable.addValue(OperationEnum.INSERT, "1233", "321");
//        memTable.addValue(OperationEnum.INSERT, "1253", "321");
//        memTable.addValue(OperationEnum.INSERT, "12367", "321");
//        memTable.addValue(OperationEnum.INSERT, "12367", "32321");
//        System.out.println(memTable.getSize());

    }

}
