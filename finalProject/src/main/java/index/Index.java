package index;

import index.io.TreeReader;
import index.io.TreeReaderImpl;
import index.io.Writer;
import index.io.WriterImpl;
import java.io.File;
import java.io.IOException;

public class Index {
    private final String name;
    private final String pathToDir;
    private final MemTable memTable;
    private final Writer writer;
    private final TreeReader treeReader;
    private final MergeService service;

    public Index(String name, String path) throws IOException{
        File dir = new File(path);
        if(!dir.exists()) {
            dir.mkdir();
        }
        this.name = name;
        this.pathToDir = path;
        this.writer = new WriterImpl();
        this.treeReader = new TreeReaderImpl();
        this.memTable = new MemTable(path, name, treeReader);
        this.service = new MergeService(writer, treeReader, name, pathToDir);
    }

    public void insert(OperationEnum operation, String key, String value) throws IOException {
        writer.logEntity(memTable.getJournal(), operation, key, value);
        memTable.addValue(operation, key, value);
        if(memTable.isFull()) {
            flushMemTableToDisk();
        }
    }

    private void flushMemTableToDisk() throws IOException {
        service.rollingMerge(memTable.getMainTree());
        writer.clearFile(memTable.getJournal());
        memTable.clearMemTable();
    }



}
