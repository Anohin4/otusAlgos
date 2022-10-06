package index;

import index.io.Reader;
import index.io.ReaderImpl;
import index.io.Writer;
import index.io.WriterImpl;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import type.tree.AvlTree;

public class MemTable {
    private AvlTree<RowEntity> mainTree;

    private Writer writer;
    private String indexName;
    private File journal;
    private int memTableThreshold;

    public MemTable(AvlTree<RowEntity> mainTree) {
        this.mainTree = mainTree;
    }

    public MemTable(String indexName) throws IOException {
        init(indexName);
    }
    public int getSize() {return mainTree.getSize();};

    private void init(String name) throws IOException {
        this.indexName = name;
        writer = new WriterImpl();
        this.memTableThreshold = 100;
        this.journal = new File(indexName + "Journal.txt");
        if (journal.exists()) {
            this.mainTree =  new ReaderImpl().readTreeFromFile(journal);
        } else {
            this.mainTree = new AvlTree<>();

        }

    }

    public void addValue(OperationEnum operation, String key, String value) throws IOException {
        writer.logEntity(journal, operation, key, value);
        RowEntity rowEntity = new RowEntity(key, value);
        if(operation.equals(OperationEnum.DELETE)) {
            rowEntity = new RowEntity(key, value, true);
        }
        mainTree.insert(rowEntity);
        if(mainTree.getSize() >= memTableThreshold) {
            flushMemTableToDisk();
        }
    }

    private void flushMemTableToDisk() throws IOException {
        writer.writeTreeToDisk(mainTree, "testName");
        clearJournal();
        this.mainTree = new AvlTree<>();

    }

    private void clearJournal() throws IOException {
        PrintWriter writer = new PrintWriter(journal);
        writer.print("");
        writer.close();
    }

}
