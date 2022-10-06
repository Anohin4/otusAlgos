import index.MemTable;
import index.OperationEnum;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        MemTable memTable = new MemTable("testTable");
        System.out.println(memTable.getSize());
        memTable.addValue(OperationEnum.INSERT, "123", "321");
        memTable.addValue(OperationEnum.INSERT, "1243", "321");
        memTable.addValue(OperationEnum.INSERT, "1233", "321");
        memTable.addValue(OperationEnum.INSERT, "1253", "321");
        memTable.addValue(OperationEnum.INSERT, "12367", "321");
        System.out.println(memTable.getSize());


    }

}
