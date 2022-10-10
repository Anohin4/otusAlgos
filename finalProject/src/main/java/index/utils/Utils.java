package index.utils;

import index.OperationEnum;
import index.RowEntity;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Utils {
    private static List<Integer> listOfSeeds;
    static {
        listOfSeeds = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("finalProject/src/main/resources/hashSeeds.txt"))) {
            stream.forEach(elem -> listOfSeeds.add(Integer.valueOf(elem)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String COLUMN_SEPARATOR = ">>>;<<<";
    public static RowEntity getRowEntityFromDescription(String descriptionFromFile) {
        String[] split = descriptionFromFile.split(COLUMN_SEPARATOR);
        return new RowEntity(split[1], split[2], OperationEnum.valueOf(split[0]));

    }
    public static int getHashSeed(int number) {
        return listOfSeeds.get(number);
    }
}
