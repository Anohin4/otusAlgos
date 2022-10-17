package index.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import type.OperationEnum;
import type.RowEntity;
import type.tree.AvlTree;
import type.tree.RowEntityForBd;
import type.tree.RowId;

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

    public static RowEntity getRowEntityForJournal(String descriptionFromFile) {
        String[] split = descriptionFromFile.split(COLUMN_SEPARATOR);
        if (split.length == 2) {
            return new RowEntity(split[0], split[1], OperationEnum.INSERT);
        }
        return new RowEntity(split[1], split[2], OperationEnum.valueOf(split[0]));
    }

    public static int getHashSeed(int number) {
        return listOfSeeds.get(number);
    }

    public static void extractTreeToSet(AvlTree tree, String indexToFind, Set<String> result, Set<String> deletedRows) {
        extractTreeToSet(tree, indexToFind, result, deletedRows, false);
    }

    public static boolean extractTreeToSet(AvlTree tree, String indexToFind, Set<String> result,
            Set<String> deletedRows, boolean fromMemtable) {
        Optional<RowEntityForBd> value = tree.getValue(indexToFind);
        if (value.isPresent()) {
            return extractRowEntityToResultMaps(result, deletedRows, value.get());
        } else {
            if (!fromMemtable) {
                System.out.println("false positive");
            }
            return false;
        }
    }

    private static boolean extractRowEntityToResultMaps(Set<String> result, Set<String> deleted, RowEntityForBd elem) {
        List<RowId> rowIdList = elem.getRowIdList();
        boolean didWeAddSmth = false;
        for (RowId entity : rowIdList) {
            if (result.contains(entity.getRowId()) || deleted.contains(entity.getRowId())) {
                continue;
            }
            if (entity.isTombStone()) {
                deleted.add(entity.getRowId());
                continue;
            }
            result.add(entity.getRowId());
            didWeAddSmth = true;
        }
        return didWeAddSmth;
    }
}
