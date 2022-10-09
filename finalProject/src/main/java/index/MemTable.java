package index;

import index.io.TreeReader;
import java.io.File;
import java.io.IOException;
import type.tree.AvlTree;

public class MemTable {

    private AvlTree mainTree;
    private File journal;

    private int memTableThreshold;

    public MemTable(AvlTree mainTree) {
        this.mainTree = mainTree;
    }

    public MemTable(String pathToDir, String name, TreeReader reader) throws IOException {
        this.memTableThreshold = 5;
        this.journal = new File(pathToDir + File.separator + name + "Journal.txt");
        if (journal.exists()) {
            this.mainTree = reader.readTreeFromFile(journal);
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
        if (operation.equals(OperationEnum.DELETE)) {
            rowEntity = new RowEntity(key, value, true);
        }
        mainTree.insert(rowEntity);
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

    public void clearMemTable() {
        this.mainTree = new AvlTree();
    }
}
