package bloomfilter;

import java.io.File;
import type.tree.AvlTree;

public interface BloomFilter {

    boolean contains(String object);
    void add(String object);

}
