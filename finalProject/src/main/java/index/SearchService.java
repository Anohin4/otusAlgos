package index;

import static index.utils.Utils.extractTreeToSet;

import bloomfilter.BloomFilter;
import index.io.TreeReader;
import index.io.Writer;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import type.tree.AvlTree;
import type.tree.RowEntityForBd;
import type.tree.RowId;

public class SearchService extends AbstractIOService {
    public SearchService(Writer writer, TreeReader reader, String indexName, String pathToDir, int maxLvl) {
        super(indexName, pathToDir, maxLvl, writer, reader);
    }
    public Set<String> findEntities(String indexValue, Set<String> result , Set<String> deleted) throws Exception {
        int currentLvl = 1;
        while (currentLvl < maxLvl) {

            int numberOfFilesThatLvl = getNumberOfFilesThatLvl(currentLvl);
            numberOfFilesThatLvl--;

            while (numberOfFilesThatLvl >= 0) {
                BloomFilter bloomFilter = reader.readBloomFilterFromDisk(
                        pathToDir + File.separator  + bloomFilterTemplateName + getLvlTemplate(currentLvl) + numberOfFilesThatLvl);
                if(bloomFilter.probablyContains(indexValue)) {
                    AvlTree avlTree = reader.readTreeFromFile(
                            new File(pathToDir+ File.separator  + indexName + getLvlTemplate(currentLvl) + numberOfFilesThatLvl));
                    extractTreeToSet(avlTree, indexValue, result, deleted);
                    numberOfFilesThatLvl--;
                }
            }
            currentLvl++;
        }
        return result;
    }
}
