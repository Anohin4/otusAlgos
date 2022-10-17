package type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MetaInfo  implements Serializable {
    private String name;
    private int[] lvlStorage;
    public void addToLvl(int currentLvl) {
        lvlStorage[currentLvl - 1] = lvlStorage[currentLvl - 1] + 1;
    }
    public void clearLvl(int lvl) {
        lvlStorage[lvl - 1] = 0;
    }

    public MetaInfo(String name, int maxLvl) {
        this.name = name;
        this.lvlStorage = new int[maxLvl];
    }

    public void setLvlValue(int lvl, int value ) {
        this.lvlStorage[lvl - 1] = value;
    }

    public int getNumberOfFilesThatLvl(int lvl) {
        return lvlStorage[lvl - 1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getLvlStorage() {
        return lvlStorage;
    }

    public void setLvlStorage(int[] lvlStorage) {
        this.lvlStorage = lvlStorage;
    }

    public void writeInfoToDisk(String pathToDir) throws FileNotFoundException {
        File file = new File(pathToDir + File.separator + name + "_meta");
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaxLvl() {
        return lvlStorage.length;
    }
}
