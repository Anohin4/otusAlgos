package index.io;

import static index.utils.Utils.getRowEntityFromDescription;

import index.RowEntity;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import type.tree.AvlTree;

public class ReaderImpl implements Reader {

    @Override
    public AvlTree<RowEntity> readTreeFromFile(File file) throws IOException {
        AvlTree<RowEntity> result = new AvlTree<>();
        try (Stream<String> stream = Files.lines(Paths.get(file.getName()))) {
            stream.forEach(elem -> result.insert(getRowEntityFromDescription(elem)));
        }
        return result;
    }
}
