package index.io;

import static index.utils.Utils.getRowEntityFromDescription;

import bloomfilter.BloomFilter;
import bloomfilter.BloomFilterImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import type.tree.AvlTree;

public class TreeReaderImpl implements TreeReader {

    @Override
    public AvlTree readTreeFromFile(File file) throws IOException {
        AvlTree result = new AvlTree();
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            stream.forEach(elem -> result.insert(getRowEntityFromDescription(elem)));
        }
        return result;
    }

    @Override
    public List<BloomFilter> readAllBloomFilters(int lvl) {
        return null;
    }

    @Override
    public BloomFilter readBloomFilterFromDisk(String fileName) throws IOException, ClassNotFoundException {
        BloomFilterImpl result;
        try(ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            result = (BloomFilterImpl) outputStream.readObject();
        }
        return result;
    }
}
