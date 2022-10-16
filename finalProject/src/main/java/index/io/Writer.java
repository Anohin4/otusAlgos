package index.io;

import bloomfilter.BloomFilter;
import type.OperationEnum;
import java.io.File;
import java.io.IOException;
import type.tree.AvlTree;

public interface Writer {

    void writeTreeToDisk(AvlTree rowEntity, String fileName) throws IOException;
    void writeBloomFilterToDisk(BloomFilter bloomFilter, String fileName) throws IOException;
}
