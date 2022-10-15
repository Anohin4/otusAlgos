package index.io;

import bloomfilter.BloomFilter;
import index.OperationEnum;
import java.io.File;
import java.io.IOException;
import type.tree.AvlTree;

public interface Writer {

    void writeTreeToDisk(AvlTree rowEntity, String fileName) throws IOException;
    void writeBloomFilterToDisk(BloomFilter bloomFilter, String fileName) throws IOException;
    void clearFile(File file) throws IOException;

    void logEntity(File journal, OperationEnum operation, String key, String value) throws IOException;
}