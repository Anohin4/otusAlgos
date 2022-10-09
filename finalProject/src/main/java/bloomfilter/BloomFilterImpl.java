package bloomfilter;

import type.tree.AvlTree;

public class BloomFilterImpl implements BloomFilter {

    public BloomFilterImpl(AvlTree avlTree) {
        initFromTree();
    }

    private void initFromTree() {

    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public void add(Object object) {

    }
}
