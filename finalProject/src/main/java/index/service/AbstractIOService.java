package index.service;

import bloomfilter.BloomFilter;
import index.io.TreeReader;
import index.io.Writer;
import java.io.File;
import java.util.Objects;
import org.apache.commons.collections4.map.LRUMap;

public abstract class AbstractIOService {

    protected String indexName;
    protected String pathToDir;
    protected int maxLvl;
    protected String bloomFilterTemplateName;
    protected Writer writer;
    protected TreeReader reader;
    protected LRUMap<String, BloomFilter> bloomFilterCache;

    public AbstractIOService(String indexName, String pathToDir, int maxLvl, Writer writer, TreeReader reader,
            LRUMap<String, BloomFilter> bloomFilterCash) {
        this.indexName = indexName;
        this.pathToDir = pathToDir;
        this.maxLvl = maxLvl;
        this.writer = writer;
        this.reader = reader;
        this.bloomFilterTemplateName = indexName + "_blm_";
        this.bloomFilterCache = bloomFilterCash;
    }

    protected boolean checkNextLvl(int currentLvl) {
        return currentLvl != maxLvl && getNumberOfFilesThatLvl(currentLvl + 1) == 10;
    }

    protected String getLvlTemplate(int lvl) {
        return "_L" + lvl + "_";
    }


    protected int getNumberOfFilesThatLvl(int lvl) {
        File dir = new File(pathToDir);
        String nameTemplate = indexName + getLvlTemplate(lvl);
        if (!dir.exists()) {
            throw new RuntimeException("Нет директории");
        }
        int length = Objects.requireNonNull(
                dir.listFiles((dir1, name) -> name.contains(nameTemplate))).length;
        //находим количество файлов этого уровня
        return length;
    }

}
