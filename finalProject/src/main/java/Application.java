import static java.util.Objects.isNull;

import index.Index;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import type.OperationEnum;
import type.RowEntity;

public class Application {

    public static void main(String[] args) throws Exception {
        //Index index = indexInsertTest("smalLevel", 2, 25000);
//        Index index = new Index("smalLevel","/Users/enanohin/IdeaProjects/otusAlgos/finalProject/indexFolder",2, 25000);
//        searchTest(index);
//        index.stop();

        String path = "/Users/enanohin/IdeaProjects/otusAlgos/finalProject/indexFolder";
        new File(path).mkdir();
        int maxLvl = 2;
        int memTableMax = 25000;
        Map<String, Index> indexMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду");
            String command = scanner.nextLine();
            if ("stop".equals(command)) {
                break;
            }
            // ОПЕРАЦИЯ ИНДЕКС КЛЮЧ ЗНАЧЕНИЕ
            String[] splitedComand = command.split(" ");
            switch (splitedComand[0].toLowerCase()) {
                case "create":
                    String name = splitedComand[1];
                    new File(path + File.separator + name).mkdir();
                    indexMap.put(name, new Index(name, path + File.separator + name, maxLvl, memTableMax));
                    break;
                case "insert":
                case "delete":
                    if (!isCommandCorrect(splitedComand, 4)) {
                        continue;
                    }
                    Index index = getIndex(indexMap, splitedComand);
                    if(isNull(index)) {
                        continue;
                    }
                    index.insert(OperationEnum.valueOf(splitedComand[0].toUpperCase()), splitedComand[2], splitedComand[3]);
                    System.out.println("Done");
                    break;
                case "select":
                    if (!isCommandCorrect(splitedComand, 3)) {
                        continue;
                    }
                    long start = System.currentTimeMillis();
                    Index indexToSelect = getIndex(indexMap, splitedComand);
                    if(isNull(indexToSelect)) {
                        continue;
                    }
                    List<RowEntity> data = indexToSelect.getData(splitedComand[2]);
                    printResult(data);
                    long end = System.currentTimeMillis() - start;
                    System.out.println("Время поиска " + end  + " милисекунд");
                    break;
                case "randominsert":
                    if (!isCommandCorrect(splitedComand, 3)) {
                        continue;
                    }
                    Index randomInsert = getIndex(indexMap, splitedComand);
                    if(isNull(randomInsert)) {
                        continue;
                    }
                    randomInsertToIndex(randomInsert, Integer.parseInt(splitedComand[2]));
                    break;
                case "randomselect":
                    if (!isCommandCorrect(splitedComand, 3)) {
                        continue;
                    }
                    Index randomSearch = getIndex(indexMap, splitedComand);
                    if(isNull(randomSearch)) {
                        continue;
                    }
                    randomSearch(randomSearch, Integer.parseInt(splitedComand[2]));
                    break;
                case ("size"):
                    if (!isCommandCorrect(splitedComand, 2)) {
                        continue;
                    }
                    if(isNull(getIndex(indexMap, splitedComand))) {
                        continue;
                    }
                    System.out.println(getIndex(indexMap, splitedComand).getSize());
                    break;
                default:
                    System.out.println("Команда не опознана");
            }
        }
        indexMap.forEach((key, value) -> value.stop());
    }

    private static void printResult(List<RowEntity> data) {
        if (data.isEmpty()) {
            System.out.println("No content");
        }
        for (RowEntity entity : data) {
            System.out.println(entity.toString());
        }
    }

    private static Index getIndex(Map<String, Index> indexMap, String[] splitedComand) {
        Index index = indexMap.get(splitedComand[1]);
        if (isNull(index)) {
            System.out.println("Неизвестный индекс");
        }
        return index;
    }

    private static boolean isCommandCorrect(String[] command, int i) {
        if (command.length != i) {
            System.out.println("Некорректная комманда");
            return false;
        }
        return true;
    }

    private static void randomInsertToIndex(Index index, int amount) throws Exception {
        long start = System.currentTimeMillis();

        index.insert(OperationEnum.INSERT, "всегдаЕсть", "плейсхолдер");
        index.insert(OperationEnum.INSERT, "всегдаУдалено", "321");
        index.insert(OperationEnum.INSERT, "1233", "321");

        int i = 0;
        while (i < amount - 4) {
            index.insert(OperationEnum.INSERT, String.valueOf(new Random().nextInt()),
                    String.valueOf(new Random().nextInt()));
            i++;
        }
        index.insert(OperationEnum.DELETE, "всегдаУдалено", "321");
        long end = System.currentTimeMillis() - start;
        System.out.println("Insertion time " + end + " milis");

    }

    private static void randomSearch(Index test, int amount) throws Exception {
        System.out.println("Start search");
        long start = System.currentTimeMillis();
        int i = 0;
        while (i < amount) {
            test.getData(String.valueOf(new Random().nextInt()));
            i++;
        }

        long end = System.currentTimeMillis() - start;
        System.out.println("Search time " + end + " milis");
    }


}
