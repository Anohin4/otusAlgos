package index.service;

import static java.util.Objects.nonNull;

import bloomfilter.BloomFilter;
import index.io.TreeReader;
import index.io.TreeReaderImpl;
import index.io.Writer;
import index.io.WriterImpl;
import java.io.File;
import java.util.Arrays;
import org.apache.commons.collections4.map.LRUMap;
import type.MetaInfo;

public abstract class AbstractIOService {

    protected String indexName;
    protected String pathToDir;
    protected int maxLvl;
    protected String bloomFilterTemplateName;
    protected Writer writer;
    protected TreeReader reader;
    protected LRUMap<String, BloomFilter> bloomFilterCache;
    protected MetaInfo metaInfo;
    protected BloomFilter masterBloomFilter;

    public AbstractIOService(String indexName, String pathToDir, int maxLvl,
            LRUMap<String, BloomFilter> bloomFilterCash, MetaInfo metaInfo, BloomFilter masterBloomFilter) {
        this.indexName = indexName;
        this.pathToDir = pathToDir;
        this.maxLvl = maxLvl;
        this.writer = new WriterImpl();
        this.reader = new TreeReaderImpl();
        this.bloomFilterTemplateName = indexName + "_blm_";
        this.bloomFilterCache = bloomFilterCash;
        this.metaInfo = metaInfo;
        this.masterBloomFilter = masterBloomFilter;
    }

    protected boolean checkNextLvl(int currentLvl) {
        return currentLvl != maxLvl && getNumberOfFilesThatLvl(currentLvl + 1) == 10;
    }

    protected String getLvlTemplate(int lvl) {
        return "_L" + lvl + "_";
    }


    protected int getNumberOfFilesThatLvl(int lvl) {
        return metaInfo.getNumberOfFilesThatLvl(lvl);
    }

    protected void removeObsoleteFiles(int lvl) {
        lvl--;
        File dir = new File(pathToDir);
        while (lvl > 0) {
            metaInfo.clearLvl(lvl);
            final int lvlToDelete = lvl;
            File[] files = dir.listFiles((dir1, name) -> name.startsWith(indexName + getLvlTemplate(lvlToDelete))
                    || name.startsWith(bloomFilterTemplateName + getLvlTemplate(lvlToDelete)));
            if (nonNull(files)) {
                Arrays.stream(files).forEach(elem -> {
                    bloomFilterCache.remove(elem.getName());
                    elem.delete();
                });
            }
            lvl--;
        }
    }

    protected String getFileName(String template, int currentLvl, int numberOfFilesThatLvl) {
        return pathToDir + File.separator + template + getLvlTemplate(currentLvl)
                + numberOfFilesThatLvl;
    }

}
