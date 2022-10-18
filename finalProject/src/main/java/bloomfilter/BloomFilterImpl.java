package bloomfilter;

import static index.utils.Utils.getHashSeed;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.util.Objects.nonNull;

import index.utils.MurmurHash;
import java.io.Serializable;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;
import type.tree.AvlTree;
import type.tree.Node;

public class BloomFilterImpl implements BloomFilter, Serializable {

    private BitSet bitSet;
    private double fpr = 0.000001;


    private int size;

    private int numberOfHashFunc;
    private int storageAmount;


    public BloomFilterImpl(int maxElements) {
        init(maxElements);
    }

    public BloomFilterImpl(AvlTree avlTree) {
        init(avlTree.getSize());
        addAllValuesFromTree(avlTree);


    }

    private void addAllValuesFromTree(AvlTree avlTree) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(avlTree.getRootNode());
        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            if (nonNull(poll.getLeftChild())) {
                nodes.add(poll.getLeftChild());
            }
            if (nonNull(poll.getRightChild())) {
                nodes.add(poll.getRightChild());
            }
            add(poll.getStorageValue().getIndexValue());
        }
    }

    private void init(int maxNumberOfElements) {

        this.size = (int) (maxNumberOfElements * log(fpr) / -log(pow(2, log(2))));
        this.numberOfHashFunc =  (int) ((size / maxNumberOfElements) * Math.log(2));
        //this.numberOfHashFunc = 15;
        this.bitSet = new BitSet(size);

    }

    @Override
    public boolean probablyContains(String object) {
        for (int i = 0; i <= numberOfHashFunc; i++) {
            long hash32 = MurmurHash.hash64(object.getBytes(), object.getBytes().length, getHashSeed(i));
            long i1 = Math.abs(hash32) % size;
            boolean result = bitSet.get(Math.abs((int) i1) % size);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void add(String object) {
        storageAmount++;
        for (int i = 0; i <= numberOfHashFunc; i++) {
            long hash32 = MurmurHash.hash64(object.getBytes(), object.getBytes().length, getHashSeed(i));
            long i1 = Math.abs(hash32) % size;
            bitSet.set((int) i1);
        }
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    @Override
    public boolean equals(Object obj) {
        BloomFilterImpl obj1 = (BloomFilterImpl) obj;
        return bitSet.equals(obj1.getBitSet());
    }

    @Override
    public int getStorageAmount() {
        return storageAmount;
    }

    public void clear() {
        bitSet.clear();
    }
}
