package index.utils;

import index.OperationEnum;
import index.RowEntity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static List<Integer> listOfSeeds;
    static {
        listOfSeeds = new ArrayList<>();
    }
    public static String COLUMN_SEPARATOR = ">>>;<<<";
    public static RowEntity getRowEntityFromDescription(String descriptionFromFile) {
        String[] split = descriptionFromFile.split(COLUMN_SEPARATOR);
        return new RowEntity(split[1], split[2], OperationEnum.valueOf(split[0]));

    }
}
