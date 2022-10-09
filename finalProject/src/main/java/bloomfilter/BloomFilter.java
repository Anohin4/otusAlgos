package bloomfilter;

import java.io.File;
import type.tree.AvlTree;

public interface BloomFilter {

    boolean contains(Object object);
    void add(Object object);

}
