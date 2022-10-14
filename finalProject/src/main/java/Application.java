import index.Index;
import index.MergeService;
import index.OperationEnum;
import index.RowEntity;
import index.io.TreeReaderImpl;
import index.io.WriterImpl;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import type.tree.AvlTree;
import type.tree.RowEntityForBd;

public class Application {

    public static void main(String[] args) throws Exception {



        indexTest();

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

    private static void ioTest() throws IOException {
        AvlTree avlTreeO = new AvlTree();
        AvlTree avlTree1O = new AvlTree();
        avlTreeO.insert( new RowEntity("123", "321"));
        avlTreeO.insert( new RowEntity( "1243", "321"));
        avlTreeO.insert(  new RowEntity("1233", "321"));
        avlTreeO.insert(  new RowEntity("1253", "321"));
        avlTreeO.insert(  new RowEntity("12367", "321"));
        avlTreeO.insert(  new RowEntity("12367", "32321"));

        avlTree1O.insert( new RowEntity("3123", "321"));
        avlTree1O.insert( new RowEntity( "31243", "321"));
        avlTree1O.insert(  new RowEntity("31233", "321"));
        avlTree1O.insert(  new RowEntity("31253", "321"));
        avlTree1O.insert(  new RowEntity("312367", "321"));
        avlTree1O.insert(  new RowEntity("312367", "32321"));
        avlTree1O.insert(  new RowEntity("12367", "123123123321"));
        WriterImpl writer = new WriterImpl();
        writer.writeTreeToDisk(avlTreeO, "tr1");
        writer.writeTreeToDisk(avlTree1O, "tr2");

        TreeReaderImpl treeReader = new TreeReaderImpl();
        AvlTree avlTree = treeReader.readTreeFromFile(new File("tr1"));
        AvlTree avlTree1 = treeReader.readTreeFromFile(new File("tr2"));

        MergeService q = new MergeService(null, null, null,null,12);
        q.mergeTrees(avlTree, avlTree1);

        Optional<RowEntityForBd> value = avlTree.getValue("312367");
        Optional<RowEntityForBd> value1 = avlTree.getValue("12367");
        System.out.println(value.get());
        System.out.println(value1.get());
    }

    private static void indexTest() throws Exception {
        Index test = new Index("testIndex", "/Users/enanohin/IdeaProjects/otusAlgos/finalProject/testindex2", 3);
        long start = System.currentTimeMillis();
        test.insert(OperationEnum.INSERT, "123", "321");
        test.insert(OperationEnum.INSERT, "1243", "321");
        test.insert(OperationEnum.INSERT, "1233", "321");
        test.insert(OperationEnum.INSERT, "1253", "321");
        test.insert(OperationEnum.INSERT, "12367", "321");
        test.insert(OperationEnum.INSERT, "12367", "32321");
        int i = 0;
        while (i < 10_000_000) {
            test.insert(OperationEnum.INSERT, String.valueOf(new Random().nextInt()),String.valueOf(new Random().nextInt()));
            i++;
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(test.getData("123"));
        System.out.println(test.getData("1243"));
        System.out.println(test.getData("1233"));
        System.out.println(test.getData("1253"));
        System.out.println(test.getData("12367"));
    }

}
