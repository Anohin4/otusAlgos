package index;

import static java.util.Objects.nonNull;

import bloomfilter.BloomFilter;
import bloomfilter.BloomFilterImpl;
import index.io.TreeReader;
import index.io.Writer;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import type.tree.AvlTree;
import type.tree.Node;

public class MergeService {

    private final String bloomFilterTemplateName = "_blm_";
    private final Writer writer;
    private final TreeReader reader;
    private final String indexName;
    private final String pathToDir;
    int maxLvl = 2;

    public MergeService(Writer writer, TreeReader reader, String indexName, String pathToDir) {
        this.writer = writer;
        this.indexName = indexName;
        this.pathToDir = pathToDir;
        this.reader = reader;
    }

    public void rollingMerge(AvlTree treeToFlush) throws IOException {
        int lvl = 1;
        boolean needMoreMerge = true;
        int count = getNumberOfFilesThatLvl(lvl);
        //если на первом уровне число файлов меньше 9, то каскадного мерджа не будет и просто пишем файл
        if (count < 9) {
            writeTree(treeToFlush, lvl, count);
        } else {
            //иначе мерджим последовательно
            //для этого проходим по всем уровням, которые надо будет с мерджить (те, где количество файлов = 9)
            //все эти деревья последовательно сливаем в одно дерево, не нарушая порядка добавления
            //и потом делаем одну запись на диск, после чего удаляем неактуальные файлы
            while (needMoreMerge && lvl < maxLvl) {
                needMoreMerge = prepareTreeToMerge(lvl, treeToFlush);
                lvl++;
            }

            //скидываем на уровень ниже
            if (lvl != maxLvl) {
                lvl++;
            }
            writeTree(treeToFlush, lvl, getNumberOfFilesThatLvl(lvl));
        }
        removeObsoleteFiles(lvl);
    }

    private boolean prepareTreeToMerge(int currentLvl, AvlTree treeToFlush) throws IOException {
        String nameTemplate = indexName + getLvlTemplate(currentLvl);
        AvlTree resultTree = reader.readTreeFromFile(new File(nameTemplate + 0));

        //читаем все деревья и мердим их по возрастанию даты обновлений
        int currentTree = 1;
        while (currentTree < 9) {
            mergeTrees(resultTree, reader.readTreeFromFile(new File(nameTemplate + currentTree)));
            currentTree++;
        }

        //мерджим последнее, самое актуальное дерево
        mergeTrees(resultTree, treeToFlush);

        //меняем у дерева рут на актуальный
        treeToFlush.setRootNode(resultTree.getRootNode());

        //проверяем, нужен ли мердж на уровне ниже
        return checkNextLvl(currentLvl);
    }


    private void writeTree(AvlTree treeToFlush, int currentLvl, int numberOfFile) throws IOException {
        writer.writeTreeToDisk(treeToFlush, indexName + getLvlTemplate(currentLvl) + numberOfFile);
        BloomFilter bloomFilter = new BloomFilterImpl(treeToFlush);
        writer.writeBloomFilterToDisk(bloomFilter,
                indexName + bloomFilterTemplateName + getLvlTemplate(currentLvl) + numberOfFile);
    }

    private boolean checkNextLvl(int currentLvl) {
        return currentLvl != maxLvl && getNumberOfFilesThatLvl(currentLvl + 1) == 10;
    }


    private void removeObsoleteFiles(int lvl) {
        lvl--;
        File dir = new File(pathToDir);
        while (lvl > 0) {
            final int lvlToDelete = lvl;
            File[] files = dir.listFiles((dir1, name) -> name.startsWith(indexName + getLvlTemplate(lvlToDelete))
                    || name.startsWith(indexName + bloomFilterTemplateName + getLvlTemplate(lvlToDelete)));
            Arrays.stream(files).forEach(File::delete);
            lvl--;
        }
    }

    private void mergeTrees(AvlTree treeFromFile, AvlTree newerTree) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(newerTree.getRootNode());
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

    private String getLvlTemplate(int lvl) {
        return "_L" + lvl + "_";
    }

    private int getNumberOfFilesThatLvl(int lvl) {
        File dir = new File(pathToDir);
        String nameTemplate = indexName + getLvlTemplate(1);
        if (!dir.exists()) {
            throw new RuntimeException("Нет директории");
        }
        //находим количество файлов этого уровня
        return Objects.requireNonNull(
                dir.listFiles((dir1, name) -> name.startsWith(nameTemplate + getLvlTemplate(lvl)))).length;
    }

}
