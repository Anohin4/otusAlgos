import bloomfilter.BloomFilter;
import bloomfilter.BloomFilterImpl;
import index.io.TreeReaderImpl;
import index.io.WriterImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        BloomFilterImpl bloomFilter = new BloomFilterImpl(10000000);
        int i =0;
        while (i < 1000000) {
            bloomFilter.add(String.valueOf(new Random().nextInt()));
            i++;
        }
        TreeReaderImpl treeReader = new TreeReaderImpl();
        WriterImpl writer = new WriterImpl();
        writer.writeBloomFilterToDisk(bloomFilter, "qwe");
        BloomFilter bloomFilter1 = treeReader.writeBloomFilterFromDisk("qwe");
        if(bloomFilter1.equals(bloomFilter)) {
            System.out.println("намана");
        } else {
            System.out.println("nope");
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
