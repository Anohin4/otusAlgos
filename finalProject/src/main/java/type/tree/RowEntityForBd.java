package type.tree;

import static index.utils.Utils.COLUMN_SEPARATOR;

import java.util.ArrayList;
import java.util.List;

public class RowEntityForBd implements Comparable<RowEntityForBd> {

    private String indexValue;
    private List<RowId> rowIdList;

    public RowEntityForBd(String indexValue, List<RowId> rowIdList) {
        this.indexValue = indexValue;
        this.rowIdList = rowIdList;
    }

    public RowEntityForBd(String logDescription) {
        String[] split = logDescription.split(COLUMN_SEPARATOR);
        this.indexValue = split[0];
        this.rowIdList = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            rowIdList.add(new RowId(split[i]));
        }
    }

    /**
     * возвращает true если был добавлен элемент и изменено количествор false усли количество не поменялось
     */
    public boolean addRowId(List<RowId> rowIdToInsertList) {
        if (rowIdList.isEmpty()) {
            rowIdList.addAll(rowIdToInsertList);
            return true;
        }
        List<RowId> listToAdd = new ArrayList<>();
        for (RowId rowIdToInsert : rowIdToInsertList) {
            //todo переделать это говно
            for (RowId id : rowIdList) {
                if (id.getRowId().equals(rowIdToInsert.getRowId())) {
                    id.setTombStone(rowIdToInsert.isTombStone());
                    break;
                }
                listToAdd.add(rowIdToInsert);
            }
        }
        rowIdList.addAll(listToAdd);
        return true;
    }

    @Override
    public int compareTo(RowEntityForBd o) {
        return this.indexValue.compareTo(o.getIndexValue());
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public List<RowId> getRowIdList() {
        return rowIdList;
    }

    public void setRowIdList(List<RowId> rowIdList) {
        this.rowIdList = rowIdList;
    }

    @Override
    public String toString() {
        StringBuilder rowList = new StringBuilder();
        for (RowId rowId : rowIdList) {
            rowList.append(COLUMN_SEPARATOR);
            rowList.append(rowId.toString());
        }
        return indexValue + rowList;
    }
}
