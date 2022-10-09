package index.io;

import static index.utils.Utils.COLUMN_SEPARATOR;
import static java.util.Objects.nonNull;

import bloomfilter.BloomFilter;
import index.OperationEnum;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import type.tree.AvlTree;
import type.tree.Node;

public class WriterImpl implements Writer {


    @Override
    public void writeTreeToDisk(AvlTree tree, String fileName) throws IOException {
        Node rootNode = tree.getRootNode();
        Queue<Node> queue = new LinkedList<>();
        queue.add(rootNode);
        //todo поменять файл
        File fileToWrite = new File(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            while (!queue.isEmpty()) {
                Node peekedRowEntity = queue.poll();
                if (nonNull(peekedRowEntity.getLeftChild())) {
                    queue.add(peekedRowEntity.getLeftChild());
                }
                if (nonNull(peekedRowEntity.getRightChild())) {
                    queue.add(peekedRowEntity.getRightChild());
                }
                writer.write(peekedRowEntity.toString());
                writer.newLine();

            }
        }
    }

    @Override
    public void writeBloomFilterToDisk(BloomFilter bloomFilter, String fileName) throws IOException {

    }

    @Override
    public void clearFile(File file) throws IOException {
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }


    @Override
    public void logEntity(File journal, OperationEnum operation, String key, String value) throws IOException {
        try (FileWriter fileWriter = new FileWriter(journal, true)) {
            fileWriter.write(operation + COLUMN_SEPARATOR + key + COLUMN_SEPARATOR + value);
            fileWriter.write(System.getProperty("line.separator"));
        }
    }


}
