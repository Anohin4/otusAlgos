package index;

import bloomfilter.BloomFilter;
import bloomfilter.BloomFilterImpl;
import index.service.MergeService;
import index.service.SearchService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.map.LRUMap;
import type.MetaInfo;
import type.OperationEnum;
import type.RowEntity;

public class Index {

    private final String pathToDir;
    private MetaInfo metaInfo;
    private int maxLvl;
    private int memTableMax;
    private final MemTable memTable;
    private final MergeService service;
    private final SearchService searchService;
    private BloomFilter masterBloomFilter;
    private final LRUMap<String, BloomFilter> bloomFilterCache;

    public String getName() {
        return metaInfo.getName();
    }

    public Index(String name, String path) throws IOException {

        this.maxLvl = 3;
        this.pathToDir = path;
        ioInit(path, name);
        this.memTableMax = 50000;
        this.memTable = new MemTable(path, name, memTableMax);
        this.bloomFilterCache = new LRUMap<>(50);
        this.service = new MergeService(name, pathToDir, maxLvl, bloomFilterCache, metaInfo, masterBloomFilter);
        this.searchService = new SearchService(name, pathToDir, maxLvl, bloomFilterCache, metaInfo, masterBloomFilter);
        this.metaInfo = new MetaInfo(name, maxLvl);
    }

    public Index(String name, String path, int maxLvl, int memTableMax) throws IOException {

        this.maxLvl = maxLvl;

        this.pathToDir = path;
        ioInit(path, name);
        this.memTableMax = 50000;
        this.bloomFilterCache = new LRUMap<>(50);
        this.memTable = new MemTable(path, name, memTableMax);
        this.service = new MergeService(name, pathToDir, maxLvl, bloomFilterCache, metaInfo, masterBloomFilter);
        this.searchService = new SearchService(name, pathToDir, maxLvl, bloomFilterCache, metaInfo, masterBloomFilter);
        this.metaInfo = new MetaInfo(name, maxLvl);

    }

    private void ioInit(String path, String name) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File metaFile = new File(path + File.separator + name + "_meta");
        if (metaFile.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(metaFile))) {
                this.metaInfo = (MetaInfo) objectInputStream.readObject();
                this.maxLvl = metaInfo.getMaxLvl();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else  {
            this.metaInfo = new MetaInfo(name, maxLvl);
            metaInfo.writeInfoToDisk(pathToDir);
        }
        File masterFilterFile = new File(path + File.separator + name + "_master");
        if(masterFilterFile.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(masterFilterFile))) {
                BloomFilterImpl bloomFilter = (BloomFilterImpl) objectInputStream.readObject();
                this.masterBloomFilter = bloomFilter;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            this.masterBloomFilter = new BloomFilterImpl(10_000_000);
        }
    }

    public void insert(OperationEnum operation, String key, String value) throws IOException {
        memTable.addValue(operation, key, value);
        if (memTable.isFull()) {
            flushMemTableToDisk();
        }
    }

    public List<RowEntity> getData(String indexValue) throws Exception {
        Set<String> result = new HashSet<>();
        Set<String> deletedRows = new HashSet<>();
        memTable.getValue(indexValue, result, deletedRows);
        searchService.findEntities(indexValue, result, deletedRows);
        return result.stream().map(elem -> new RowEntity(indexValue, elem)).collect(Collectors.toList());
    }

    private void flushMemTableToDisk() throws IOException {
        service.rollingMerge(memTable.getMainTree());
        memTable.clearMemTable();

    }

    public void stop() {
        memTable.stop();
    }


}
