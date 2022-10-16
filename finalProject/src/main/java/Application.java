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
        Index smalLevel = indexTest("smalLevel",2, 50000);

        System.out.println(smalLevel.getData("1233"));

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

    private static void ioTest() throws IOException, ClassNotFoundException {
//        AvlTree avlTreeO = new AvlTree();
//        AvlTree avlTree1O = new AvlTree();
//        avlTreeO.insert( new RowEntity("123", "321"));
//        avlTreeO.insert( new RowEntity( "1243", "321"));
//        avlTreeO.insert(  new RowEntity("1233", "321"));
//        avlTreeO.insert(  new RowEntity("1253", "321"));
//        avlTreeO.insert(  new RowEntity("12367", "321"));
//        avlTreeO.insert(  new RowEntity("12367", "32321"));
//
//        avlTree1O.insert( new RowEntity("3123", "321"));
//        avlTree1O.insert( new RowEntity( "31243", "321"));
//        avlTree1O.insert(  new RowEntity("31233", "321"));
//        avlTree1O.insert(  new RowEntity("31253", "321"));
//        avlTree1O.insert(  new RowEntity("312367", "321"));
//        avlTree1O.insert(  new RowEntity("312367", "32321"));
//        avlTree1O.insert(  new RowEntity("12367", "123123123321"));
//        WriterImpl writer = new WriterImpl();
//        writer.writeTreeToDisk(avlTreeO, "tr1");
//        writer.writeTreeToDisk(avlTree1O, "tr2");

        TreeReaderImpl treeReader = new TreeReaderImpl();
        long start1 = System.currentTimeMillis();
        AvlTree avlTree = treeReader.readTreeFromFile(
                new File("/Users/enanohin/IdeaProjects/otusAlgos/finalProject/testindex2/testIndex_L3_0"));
        BloomFilter bloomFilter = treeReader.readBloomFilterFromDisk(
                "/Users/enanohin/IdeaProjects/otusAlgos/finalProject/testindex2/testIndex_blm__L3_0");
        long l = System.currentTimeMillis() - start1;
        System.out.println("My read " + l);

        Optional<RowEntityForBd> value = avlTree.getValue("123");
        Optional<RowEntityForBd> value1 = avlTree.getValue("12367");
        System.out.println(bloomFilter.probablyContains("123"));
        System.out.println(bloomFilter.probablyContains("12367"));
        System.out.println(value.get());
        System.out.println(value1.get());
    }

    private static void indexSearchTest() throws Exception {
        Index test = new Index("testIndex", "/Users/enanohin/IdeaProjects/otusAlgos/finalProject/testindex2", 3);
        List<RowEntity> data = test.getData("123");
        System.out.println(data.toString());
    }

    private static Index indexTest(String indexName, int maxLvl, int memTableMax) throws Exception {
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
        long start = System.currentTimeMillis();
        test.getData("123");
        test.getData("1243");
        test.getData("1233");
        test.getData("1253");
        test.getData("12367");
        long end = System.currentTimeMillis() - start;
        System.out.println(" index name  " + test.getName() + ", find 5 elems " + end);
    }


}
