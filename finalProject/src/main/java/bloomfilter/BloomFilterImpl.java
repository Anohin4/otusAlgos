package bloomfilter;

import static index.utils.Utils.getHashSeed;
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
    double fpr = 0.001;

    int size;
    int numberOfHashFunc;


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
        this.size = (int) ((maxNumberOfElements * Math.abs(Math.log(fpr))) / (Math.pow(Math.log(2), 2)));
        this.numberOfHashFunc =  (int) ((size / maxNumberOfElements) * Math.log(2));
        this.bitSet = new BitSet(maxNumberOfElements);
    }

    @Override
    public boolean probablyContains(String object) {
        for (int i =0; i <= numberOfHashFunc; i ++) {
            int hash32 = MurmurHash.hash32(object.getBytes(), object.getBytes().length, getHashSeed(i));
            boolean result = bitSet.get(Math.abs(hash32)%size);
            if(!result) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void add(String object) {
        for (int i =0; i <= numberOfHashFunc; i ++) {
            int hash32 = MurmurHash.hash32(object.getBytes(), object.getBytes().length, getHashSeed(i));
            bitSet.set(Math.abs(hash32)%size);
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
}
