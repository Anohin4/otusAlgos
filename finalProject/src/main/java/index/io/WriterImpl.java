package index.io;

import static index.utils.Utils.COLUMN_SEPARATOR;
import static java.util.Objects.nonNull;

import bloomfilter.BloomFilter;
import type.JournalEntity;
import type.OperationEnum;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(bloomFilter);
        }
    }




}
