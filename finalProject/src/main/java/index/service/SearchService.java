package index.service;

import static index.utils.Utils.extractTreeToSet;

import bloomfilter.BloomFilter;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.collections4.map.LRUMap;
import type.MetaInfo;
import type.tree.AvlTree;

public class SearchService extends AbstractIOService {

    public SearchService(String indexName, String pathToDir, int maxLvl,
            LRUMap<String, BloomFilter> bloomFilterCache, MetaInfo metaInfo, BloomFilter masterBloomFilter) {
        super(indexName, pathToDir, maxLvl, bloomFilterCache, metaInfo, masterBloomFilter);
    }

    public void findEntities(String indexValue, Set<String> result, Set<String> deleted) throws Exception {
        if (!masterBloomFilter.probablyContains(indexValue)) {
            return;
        }

        int currentLvl = 1;
        while (currentLvl <= maxLvl) {

            int numberOfFilesThatLvl = getNumberOfFilesThatLvl(currentLvl);
            numberOfFilesThatLvl--;

            while (numberOfFilesThatLvl >= 0) {
                String bloomFilterName =
                        getFileName(bloomFilterTemplateName, currentLvl, numberOfFilesThatLvl);
                String fileName =
                        getFileName(indexName, currentLvl, numberOfFilesThatLvl);

                BloomFilter bloomFilter = getBloomFilter(bloomFilterName);
                if (bloomFilter.probablyContains(indexValue)) {
                    AvlTree avlTree = reader.readTreeFromFile(
                            new File(fileName));
                    extractTreeToSet(avlTree, indexValue, result, deleted);

                }
                numberOfFilesThatLvl--;
            }
            currentLvl++;
        }
    }



    private BloomFilter getBloomFilter(String bloomFilterName) throws IOException, ClassNotFoundException {
        BloomFilter bloomFilter;
        if (bloomFilterCache.containsKey(bloomFilterName)) {
            bloomFilter = bloomFilterCache.get(bloomFilterName);
        } else {
            bloomFilter = reader.readBloomFilterFromDisk(
                    bloomFilterName);
            //если кэш не полный - закидываем, что прочли, лишним не будет
            if (!bloomFilterCache.isFull()) {
                bloomFilterCache.put(bloomFilterName, bloomFilter);
            }
        }
        return bloomFilter;
    }
}
