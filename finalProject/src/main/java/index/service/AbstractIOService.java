package index.service;

import bloomfilter.BloomFilter;
import index.io.TreeReader;
import index.io.TreeReaderImpl;
import index.io.Writer;
import index.io.WriterImpl;
import java.io.File;
import java.util.Objects;
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

    public AbstractIOService(String indexName, String pathToDir, int maxLvl,
            LRUMap<String, BloomFilter> bloomFilterCash, MetaInfo metaInfo) {
        this.indexName = indexName;
        this.pathToDir = pathToDir;
        this.maxLvl = maxLvl;
        this.writer = new WriterImpl();
        this.reader = new TreeReaderImpl();
        this.bloomFilterTemplateName = indexName + "_blm_";
        this.bloomFilterCache = bloomFilterCash;
        this.metaInfo = metaInfo;
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

}
