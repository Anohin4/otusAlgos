package bloomfilter;

import index.utils.MurmurHash;
import java.util.BitSet;
import type.tree.AvlTree;

public class BloomFilterImpl implements BloomFilter {
    private BitSet bitSet;
    double fpr = 0.001;

    int size;
    int numberOfHashFunc;


    public BloomFilterImpl(int maxElements) {
        this.bitSet = new BitSet();
    }

    public BloomFilterImpl(AvlTree avlTree) {
        init(avlTree.getSize());

    }

    private void init(int maxNumberOfElements) {
        this.size = (int) ((maxNumberOfElements * Math.abs(Math.log(fpr))) / (Math.pow(Math.log(2), 2)));
        this.numberOfHashFunc =  (int) ((size / maxNumberOfElements) * Math.log(2));
    }

    @Override
    public boolean contains(String object) {
        return false;
    }

    @Override
    public void add(String object) {
        int i = object.hashCode() % size;
        bitSet.toByteArray();
        //MurmurHash.hash32(object.getBytes(), object.getBytes().length, )
       // BitSet n = BitSet.valueOf()
    }
}
