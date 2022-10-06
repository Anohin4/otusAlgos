package index.utils;

import index.OperationEnum;
import index.RowEntity;

public class Utils {
    public static String COLUMN_SEPARATOR = ">>>;<<<";
    public static RowEntity getRowEntityFromDescription(String descriptionFromFile) {
        String[] split = descriptionFromFile.split(COLUMN_SEPARATOR);
        return new RowEntity(split[1], split[2], OperationEnum.valueOf(split[0]));

    }
}
