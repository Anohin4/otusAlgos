package index;

import bloomfilter.BloomFilter;
import index.io.TreeReader;
import index.io.TreeReaderImpl;
import index.io.Writer;
import index.io.WriterImpl;
import index.service.MergeService;
import index.service.SearchService;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.map.LRUMap;
import type.OperationEnum;
import type.RowEntity;

public class Index {

    private final String name;
    private final String pathToDir;
    private int maxLvl;
    private int memTableMax;
    private final MemTable memTable;
    private final Writer writer;
    private final TreeReader treeReader;
    private final MergeService service;
    private final SearchService searchService;
    private final LRUMap<String, BloomFilter> bloomFilterCache;

    public String getName() {
        return name;
    }

    public Index(String name, String path) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.name = name;
        this.maxLvl = 3;
        this.pathToDir = path;
        this.writer = new WriterImpl();
        this.treeReader = new TreeReaderImpl();
        this.memTableMax = 50000;
        this.memTable = new MemTable(path, name, treeReader, memTableMax);
        this.bloomFilterCache = new LRUMap<>(50);
        this.service = new MergeService(writer, treeReader, name, pathToDir, maxLvl, bloomFilterCache);
        this.searchService = new SearchService(writer, treeReader, name, pathToDir, maxLvl, bloomFilterCache);

    }

    public Index(String name, String path, int maxLvl) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.name = name;
        this.maxLvl = maxLvl;
        this.pathToDir = path;
        this.writer = new WriterImpl();
        this.treeReader = new TreeReaderImpl();
        this.memTableMax = 50000;
        this.bloomFilterCache = new LRUMap<>(50);
        this.memTable = new MemTable(path, name, treeReader, memTableMax);
        this.service = new MergeService(writer, treeReader, name, pathToDir, maxLvl, bloomFilterCache);
        this.searchService = new SearchService(writer, treeReader, name, pathToDir, maxLvl, bloomFilterCache);

    }

    public Index(String name, String path, int maxLvl, int memTableMax) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.name = name;
        this.maxLvl = maxLvl;
        this.pathToDir = path;
        this.writer = new WriterImpl();
        this.treeReader = new TreeReaderImpl();
        this.memTableMax = 50000;
        this.bloomFilterCache = new LRUMap<>(50);
        this.memTable = new MemTable(path, name, treeReader, memTableMax);
        this.service = new MergeService(writer, treeReader, name, pathToDir, maxLvl, bloomFilterCache);
        this.searchService = new SearchService(writer, treeReader, name, pathToDir, maxLvl, bloomFilterCache);


    }

    public void insert(OperationEnum operation, String key, String value) throws IOException {
        //writer.logEntity(memTable.getJournal(), operation, key, value);
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
        //writer.clearFile(memTable.getJournal());
        memTable.clearMemTable();
    }

    public void stop() {
        memTable.stop();
    }


}
