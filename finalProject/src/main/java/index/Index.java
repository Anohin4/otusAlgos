package index;

import index.io.TreeReader;
import index.io.TreeReaderImpl;
import index.io.Writer;
import index.io.WriterImpl;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Index {
    private final String name;
    private final String pathToDir;
    private int maxLvl;
    private final MemTable memTable;
    private final Writer writer;
    private final TreeReader treeReader;
    private final MergeService service;
    private final SearchService searchService;

    public Index(String name, String path) throws IOException{
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
        this.name = name;
        this.maxLvl = 3;
        this.pathToDir = path;
        this.writer = new WriterImpl();
        this.treeReader = new TreeReaderImpl();
        this.memTable = new MemTable(path, name, treeReader);
        this.service = new MergeService(writer, treeReader, name, pathToDir, maxLvl);
        this.searchService = new SearchService(writer, treeReader, name, pathToDir, maxLvl);

    }
    public Index(String name, String path, int maxLvl) throws IOException{
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
        this.name = name;
        this.maxLvl = maxLvl;
        this.pathToDir = path;
        this.writer = new WriterImpl();
        this.treeReader = new TreeReaderImpl();
        this.memTable = new MemTable(path, name, treeReader);
        this.service = new MergeService(writer, treeReader, name, pathToDir, maxLvl);
        this.searchService = new SearchService(writer, treeReader, name, pathToDir, maxLvl);

    }

    public void insert(OperationEnum operation, String key, String value) throws IOException {
        //writer.logEntity(memTable.getJournal(), operation, key, value);
        memTable.addValue(operation, key, value);
        if(memTable.isFull()) {
            flushMemTableToDisk();
        }
    }
    public List<RowEntity> getData(String indexValue) throws Exception{
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




}