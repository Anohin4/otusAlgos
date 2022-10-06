package index.io;

import index.OperationEnum;
import index.RowEntity;
import java.io.File;
import java.io.IOException;
import type.tree.AvlTree;

public interface Writer {

    void writeTreeToDisk(AvlTree rowEntity, String testName) throws IOException;

    void logEntity(File journal, OperationEnum operation, String key, String value) throws IOException;
}
