package index.io;

import static index.utils.Utils.getRowEntityFromDescription;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import type.tree.AvlTree;

public class TreeReaderImpl implements TreeReader {

    @Override
    public AvlTree readTreeFromFile(File file) throws IOException {
        AvlTree result = new AvlTree();
        try (Stream<String> stream = Files.lines(Paths.get(file.getName()))) {
            stream.forEach(elem -> result.insert(getRowEntityFromDescription(elem)));
        }
        return result;
    }
}
