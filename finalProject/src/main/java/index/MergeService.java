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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import type.tree.AvlTree;
import type.tree.Node;

public class MergeService  extends AbstractIOService {
    long timer;
    public MergeService(Writer writer, TreeReader reader, String indexName, String pathToDir, int maxLvl) {
        super(indexName, pathToDir, maxLvl, writer, reader);

        this.timer = System.currentTimeMillis();
    }

    public void rollingMerge(AvlTree treeToFlush) throws IOException {
        int lvl = 1;
        boolean needMoreMerge = true;
        int count = getNumberOfFilesThatLvl(lvl);
        //если на первом уровне число файлов меньше 9, то каскадного мерджа не будет и просто пишем файл
        if (count < 9) {
            writeTree(treeToFlush, lvl, count);
        } else {
            System.out.println(System.currentTimeMillis() - timer);
            //иначе мерджим последовательно
            //для этого проходим по всем уровням, которые надо будет с мерджить (те, где количество файлов = 9)
            //все эти деревья последовательно сливаем в одно дерево, не нарушая порядка добавления
            //и потом делаем одну запись на диск, после чего удаляем неактуальные файлы
            while (needMoreMerge && lvl < maxLvl) {
                needMoreMerge = prepareTreeToMerge(lvl, treeToFlush);
                lvl++;
            }

            //скидываем на уровень ниже
//            if (lvl != maxLvl) {
//                lvl++;
//            }
            writeTree(treeToFlush, lvl, getNumberOfFilesThatLvl(lvl));
        }
        removeObsoleteFiles(lvl);
    }

    private void writeTree(AvlTree treeToFlush, int currentLvl, int numberOfFile) throws IOException {
        writer.writeTreeToDisk(treeToFlush, pathToDir+ File.separator  + indexName + getLvlTemplate(currentLvl) + numberOfFile);
        BloomFilter bloomFilter = new BloomFilterImpl(treeToFlush);
        writer.writeBloomFilterToDisk(bloomFilter,
                pathToDir + File.separator + bloomFilterTemplateName + getLvlTemplate(currentLvl) + numberOfFile);
    }

    public void mergeTrees(AvlTree treeFromFile, AvlTree newerTree) {
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



    private boolean prepareTreeToMerge(int currentLvl, AvlTree treeToFlush) throws IOException {
        String nameTemplate = indexName + getLvlTemplate(currentLvl);
        AvlTree resultTree = reader.readTreeFromFile(new File(pathToDir+ File.separator +nameTemplate + 0));

        //читаем все деревья и мердим их по возрастанию даты обновлений
        int currentTree = 1;
        while (currentTree < 9) {
            mergeTrees(resultTree, reader.readTreeFromFile(new File(pathToDir+ File.separator +nameTemplate + currentTree)));
            currentTree++;
        }

        //мерджим последнее, самое актуальное дерево
        mergeTrees(resultTree, treeToFlush);

        //меняем у дерева рут на актуальный
        treeToFlush.setRootNode(resultTree.getRootNode());

        //проверяем, нужен ли мердж на уровне ниже
        return checkNextLvl(currentLvl);
    }

}
