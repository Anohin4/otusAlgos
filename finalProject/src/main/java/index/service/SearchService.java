package index.service;

import static index.utils.Utils.extractTreeToSet;

import bloomfilter.BloomFilter;
import index.io.TreeReader;
import index.io.Writer;
import java.io.File;
import java.util.Set;
import org.apache.commons.collections4.map.LRUMap;
import type.tree.AvlTree;

public class SearchService extends AbstractIOService {
    public SearchService(Writer writer, TreeReader reader, String indexName, String pathToDir, int maxLvl,
            LRUMap<String, BloomFilter> bloomFilterCache) {
        super(indexName, pathToDir, maxLvl, writer, reader,bloomFilterCache);
    }
    public Set<String> findEntities(String indexValue, Set<String> result , Set<String> deleted) throws Exception {
        int currentLvl = 1;
        while (currentLvl <= maxLvl) {

            int numberOfFilesThatLvl = getNumberOfFilesThatLvl(currentLvl);
            numberOfFilesThatLvl--;

            while (numberOfFilesThatLvl >= 0) {
                String bloomFilterName = pathToDir + File.separator + bloomFilterTemplateName + getLvlTemplate(currentLvl)
                        + numberOfFilesThatLvl;
                BloomFilter bloomFilter;
                if(bloomFilterCache.containsKey(bloomFilterName)) {
                    bloomFilter = bloomFilterCache.get(bloomFilterName);
                }else {
                    bloomFilter = reader.readBloomFilterFromDisk(
                            bloomFilterName);
                    bloomFilterCache.put(bloomFilterName, bloomFilter);
                }
                if(bloomFilter.probablyContains(indexValue)) {
                    String fileName =
                            pathToDir + File.separator + indexName + getLvlTemplate(currentLvl) + numberOfFilesThatLvl;
                    AvlTree avlTree = reader.readTreeFromFile(
                            new File(pathToDir+ File.separator  + indexName + getLvlTemplate(currentLvl) + numberOfFilesThatLvl));
                    extractTreeToSet(avlTree, indexValue, result, deleted);

                }
                numberOfFilesThatLvl--;
            }
            currentLvl++;
        }
        return result;
    }
}
