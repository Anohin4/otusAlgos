package index;

import static index.utils.Utils.extractTreeToSet;

import index.io.TreeReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import type.tree.AvlTree;
import type.tree.RowEntityForBd;

public class MemTable {

    private AvlTree mainTree;
    private File journal;

    private int memTableThreshold;

    public MemTable(AvlTree mainTree) {
        this.mainTree = mainTree;
    }

    public MemTable(String pathToDir, String name, TreeReader reader) throws IOException {
        this.memTableThreshold = 5000;
        this.journal = new File(pathToDir  + File.separator + name + "Journal.txt");
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

    public synchronized void addValue(OperationEnum operation, String key, String value) throws IOException {
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

    public void getValue(String indexValue, Set<String> result, Set<String> deletedRows) {
        extractTreeToSet(mainTree, indexValue, result, deletedRows);
    }
}
