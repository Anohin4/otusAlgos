import index.Index;
import index.OperationEnum;
import java.io.IOException;
import java.util.Random;

public class Application {

    public static void main(String[] args) throws Exception {
        Index test = new Index("testIndex", "/Users/enanohin/IdeaProjects/otusAlgos/finalProject/testindex2", 3);

        test.insert(OperationEnum.INSERT, "123", "321");
        test.insert(OperationEnum.INSERT, "1243", "321");
        test.insert(OperationEnum.INSERT, "1233", "321");
        test.insert(OperationEnum.INSERT, "1253", "321");
        test.insert(OperationEnum.INSERT, "12367", "321");
        test.insert(OperationEnum.INSERT, "12367", "32321");
        int i = 0;
        while (i < 30000) {
            test.insert(OperationEnum.INSERT, String.valueOf(new Random().nextInt()),String.valueOf(new Random().nextInt()));
            i++;
        }
        System.out.println(test.getData("123"));
        System.out.println(test.getData("1243"));
        System.out.println(test.getData("1233"));
        System.out.println(test.getData("1253"));
        System.out.println(test.getData("12367"));


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
