package index;

import static java.util.Objects.nonNull;

import index.io.ReaderImpl;
import index.io.Writer;
import index.io.WriterImpl;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import type.tree.AvlTree;
import type.tree.Node;

public class MergeService {

    private final int maxThreshold = 500_000;
    private String lvlTemplate = "_L";
    private String bloomfilterTemplateName = "_blm_";
    private Writer writer = new WriterImpl();

    /**
     * dj
     *
     * @return создан ли новый файл или нет
     */
    public boolean rollingMerge(AvlTree treeToFlush, String indexName) throws IOException {
        int currentLvl = 1;
        AvlTree tempTree;
        while (true) {
            int lvlThreshold = 5000 * (int) Math.pow(10, currentLvl);
            String fileName = indexName + lvlTemplate + currentLvl;
            File file = new File(fileName);
            if (!file.exists()) {
                writer.writeTreeToDisk(treeToFlush, fileName);
                return true;
            }

            tempTree = mergeTrees(new ReaderImpl().readTreeFromFile(file), treeToFlush);
            if (tempTree.getSize() >= lvlThreshold) {
                currentLvl++;
            } else {
                writer.writeTreeToDisk(tempTree, fileName);
                break;
            }
        }
        deleteObsoleteFiles(currentLvl, indexName);
    }

    private void deleteObsoleteFiles(int currentLvl, String indexName) {
        currentLvl--;
        while (currentLvl > 0) {
            File file = new File(indexName + lvlTemplate + currentLvl);
            file.delete();
            currentLvl--;
        }
    }

    private AvlTree mergeTrees(AvlTree treeFromFile, AvlTree treeToFlush) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(treeToFlush.getRootNode());
        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            if (nonNull(poll.getLeftChild())) {
                nodes.add(poll.getLeftChild());
            }
            if (nonNull(poll.getRightChild())) {
                nodes.add(poll.getRightChild());
            }
            treeFromFile.insert(poll.getStorageValue());
        }
    }

}
