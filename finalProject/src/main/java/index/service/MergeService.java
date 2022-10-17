package index.service;

import static java.util.Objects.nonNull;

import bloomfilter.BloomFilter;
import bloomfilter.BloomFilterImpl;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.collections4.map.LRUMap;
import type.MetaInfo;
import type.tree.AvlTree;
import type.tree.Node;

public class MergeService extends AbstractIOService {

    public MergeService(String indexName, String pathToDir, int maxLvl,
            LRUMap<String, BloomFilter> bloomFilterCache, MetaInfo metaInfo) {
        super(indexName, pathToDir, maxLvl, bloomFilterCache, metaInfo);

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

            writeTree(treeToFlush, lvl, getNumberOfFilesThatLvl(lvl));
        }
        removeObsoleteFiles(lvl);
        metaInfo.writeInfoToDisk(pathToDir);
    }

    private void writeTree(AvlTree treeToFlush, int currentLvl, int numberOfFile) throws IOException {
        writer.writeTreeToDisk(treeToFlush,
                pathToDir + File.separator + indexName + getLvlTemplate(currentLvl) + numberOfFile);
        BloomFilter bloomFilter = new BloomFilterImpl(treeToFlush);
        String filterName =
                pathToDir + File.separator + bloomFilterTemplateName + getLvlTemplate(currentLvl) + numberOfFile;
        if (!bloomFilterCache.isFull()) {
            bloomFilterCache.put(filterName, bloomFilter);
        }
        writer.writeBloomFilterToDisk(bloomFilter,
                filterName);
        metaInfo.addToLvl(currentLvl);

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
        AvlTree resultTree = reader.readTreeFromFile(new File(pathToDir + File.separator + nameTemplate + 0));

        //читаем все деревья и мердим их по возрастанию даты обновлений
        int currentTree = 1;
        while (currentTree < 9) {
            mergeTrees(resultTree,
                    reader.readTreeFromFile(new File(pathToDir + File.separator + nameTemplate + currentTree)));
            currentTree++;
        }

        //мерджим последнее, самое актуальное дерево
        mergeTrees(resultTree, treeToFlush);

        //меняем у дерева рут на актуальный
        treeToFlush.setRootNode(resultTree.getRootNode());
        treeToFlush.setSize(resultTree.getSize());

        //проверяем, нужен ли мердж на уровне ниже
        return checkNextLvl(currentLvl);
    }


    protected void removeObsoleteFiles(int lvl) {
        lvl--;
        File dir = new File(pathToDir);
        while (lvl > 0) {
            metaInfo.clearLvl(lvl);
            final int lvlToDelete = lvl;
            File[] files = dir.listFiles((dir1, name) -> name.startsWith(indexName + getLvlTemplate(lvlToDelete))
                    || name.startsWith(bloomFilterTemplateName + getLvlTemplate(lvlToDelete)));
            if (nonNull(files)) {
                Arrays.stream(files).forEach(elem -> {
                    bloomFilterCache.remove(elem.getName());
                    elem.delete();
                });
            }
            lvl--;
        }
    }

}
