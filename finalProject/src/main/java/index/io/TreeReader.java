package index.io;

import java.io.File;
import java.io.IOException;
import type.tree.AvlTree;

public interface TreeReader {

    AvlTree readTreeFromFile(File file) throws IOException;

}
