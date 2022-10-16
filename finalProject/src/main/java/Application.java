import bloomfilter.BloomFilter;
import index.Index;
import index.io.TreeReaderImpl;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import type.OperationEnum;
import type.RowEntity;
import type.tree.AvlTree;
import type.tree.RowEntityForBd;

public class Application {

    public static void main(String[] args) throws Exception {
       Index smalLevel = indexInsertTest("smalLevel", 2, 50000);
      //  Index smalLevel = new Index("smalLevel","/Users/enanohin/IdeaProjects/otusAlgos/finalProject/testindex2",2, 50000);
        System.out.println(smalLevel.getData("123"));
        search5ElemTest(smalLevel);
        smalLevel.stop();


    }

    private static Index indexInsertTest(String indexName, int maxLvl, int memTableMax) throws Exception {
        long start = System.currentTimeMillis();
        Index test = new Index(indexName, "/Users/enanohin/IdeaProjects/otusAlgos/finalProject/testindex2", maxLvl,
                memTableMax);

        test.insert(OperationEnum.INSERT, "123", "321");
        test.insert(OperationEnum.INSERT, "1243", "321");
        test.insert(OperationEnum.INSERT, "1233", "321");
        test.insert(OperationEnum.INSERT, "1253", "321");
        test.insert(OperationEnum.INSERT, "12367", "321");
        test.insert(OperationEnum.INSERT, "12367", "32321");

        int i = 0;
        while (i < 5_000_000) {
            test.insert(OperationEnum.INSERT, String.valueOf(new Random().nextInt()),
                    String.valueOf(new Random().nextInt()));
            i++;
        }
        test.insert(OperationEnum.DELETE, "1233", "321");
        while (i < 10_000_000) {
            test.insert(OperationEnum.INSERT, String.valueOf(new Random().nextInt()),
                    String.valueOf(new Random().nextInt()));
            i++;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(" index name  " + test.getName() + ", creation time " + end);
        return test;

    }

    private static void search5ElemTest(Index test) throws Exception {
        System.out.println("start search test");
        long start = System.currentTimeMillis();
        int i = 0;
        while (i < 10_000_000) {
            test.getData(String.valueOf(new Random().nextInt()));
            i++;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(" index name  " + test.getName() + ", find 10_000_000 elems " + end);
    }


}
