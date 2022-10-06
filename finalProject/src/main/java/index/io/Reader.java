package index.io;

import index.RowEntity;
import java.io.File;
import java.io.IOException;
import type.tree.AvlTree;

public interface Reader {

    AvlTree<RowEntity> readTreeFromFile(File file) throws IOException;

}
