package index.io;

import bloomfilter.BloomFilter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import type.tree.AvlTree;

public interface TreeReader {

    AvlTree readTreeFromFile(File file) throws IOException;
    List<BloomFilter> readAllBloomFilters(int lvl);

    BloomFilter writeBloomFilterFromDisk(String fileName) throws IOException, ClassNotFoundException;
}
