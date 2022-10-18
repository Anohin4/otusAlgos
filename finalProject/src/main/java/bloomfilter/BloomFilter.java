package bloomfilter;

import java.util.BitSet;

public interface BloomFilter {

    boolean probablyContains(String object);

    void add(String object);

    BitSet getBitSet();
    void clear();

    int getStorageAmount();
}
