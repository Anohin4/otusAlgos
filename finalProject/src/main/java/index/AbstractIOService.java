package index;

import index.io.TreeReader;
import index.io.Writer;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractIOService {
    protected String indexName;
    protected String pathToDir;
    protected int maxLvl;
    protected String bloomFilterTemplateName ;
    protected Writer writer;
    protected TreeReader reader;

    public AbstractIOService(String indexName, String pathToDir, int maxLvl, Writer writer, TreeReader reader) {
        this.indexName = indexName;
        this.pathToDir = pathToDir;
        this.maxLvl = maxLvl;
        this.writer = writer;
        this.reader = reader;
        this.bloomFilterTemplateName = indexName + "_blm_";
    }

    protected boolean checkNextLvl(int currentLvl) {
        return currentLvl != maxLvl && getNumberOfFilesThatLvl(currentLvl + 1) == 10;
    }


    protected void removeObsoleteFiles(int lvl) {
        lvl--;
        File dir = new File(pathToDir);
        while (lvl > 0) {
            final int lvlToDelete = lvl;
            File[] files = dir.listFiles((dir1, name) -> name.startsWith(indexName + getLvlTemplate(lvlToDelete))
                    || name.startsWith(bloomFilterTemplateName + getLvlTemplate(lvlToDelete)));
            Arrays.stream(files).forEach(File::delete);
            lvl--;
        }
    }

    protected String getLvlTemplate(int lvl) {
        return "_L" + lvl + "_";
    }


    protected int getNumberOfFilesThatLvl(int lvl) {
        File dir = new File(pathToDir);
        String nameTemplate = indexName + getLvlTemplate(1);
        if (!dir.exists()) {
            throw new RuntimeException("Нет директории");
        }
        int length = Objects.requireNonNull(
                dir.listFiles((dir1, name) -> name.contains(nameTemplate))).length;
        //находим количество файлов этого уровня
        return length;
    }

}
