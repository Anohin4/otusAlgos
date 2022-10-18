package index;

import static index.utils.Utils.extractTreeToSet;

import bloomfilter.BloomFilter;
import bloomfilter.BloomFilterImpl;
import index.io.JournalWriter;
import index.io.TreeReader;
import index.io.TreeReaderImpl;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import type.JournalEntity;
import type.OperationEnum;
import type.RowEntity;
import type.tree.AvlTree;

public class MemTable {

    private AvlTree mainTree;
    private File journal;

    private int memTableThreshold;
    private BloomFilter bloomFilter;
    private ConcurrentLinkedQueue<JournalEntity> journalQueue;
    private JournalWriter journalWriter;

    public MemTable(String pathToDir, String name,  int memTableThreshold) throws IOException {
        this.memTableThreshold = memTableThreshold;
        this.journal = new File(pathToDir + File.separator + name + "Journal.txt");
        initiateJournal();
        this.journalQueue = new ConcurrentLinkedQueue<>();
        this.journalWriter = new JournalWriter(journalQueue, journal);
        Thread thread = new Thread(journalWriter);
        thread.start();
        this.bloomFilter = new BloomFilterImpl(memTableThreshold);
    }

    private void initiateJournal() throws IOException {
        TreeReader reader = new TreeReaderImpl();
        if (journal.exists()) {
            this.mainTree = reader.readTreeForJournal(journal);
        } else {
            journal.createNewFile();
            this.mainTree = new AvlTree();
        }
    }

    public int getSize() {
        return mainTree.getSize();
    }

    public void addValue(OperationEnum operation, String key, String value) throws IOException {
        RowEntity rowEntity = new RowEntity(key, value);
        journalQueue.add(new JournalEntity(operation, key, value));
        if (operation.equals(OperationEnum.DELETE)) {
            rowEntity = new RowEntity(key, value, true);
        }
        mainTree.insert(rowEntity);
        bloomFilter.add(key);
    }

    public boolean isFull() {
        return mainTree.getSize() >= memTableThreshold;
    }

    public File getJournal() {
        return journal;
    }

    public AvlTree getMainTree() {
        return mainTree;
    }

    public void clearMemTable() throws IOException {
        this.mainTree = new AvlTree();
        journalWriter.clearFile();
    }

    public void getValue(String indexValue, Set<String> result, Set<String> deletedRows) {
        if(!bloomFilter.probablyContains(indexValue)) {
            return;
        }
        extractTreeToSet(mainTree, indexValue, result, deletedRows, true);
    }

    public void stop() {
        journalWriter.stop();
    }
}
