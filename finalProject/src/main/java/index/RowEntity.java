package index;

import static index.utils.Utils.COLUMN_SEPARATOR;

public class RowEntity implements Comparable<RowEntity> {
    private String rowId;
    private String value;
    private Boolean deleted;

    public RowEntity(String rowId, String value) {
        this.rowId = rowId;
        this.value = value;
        this.deleted = false;
    }

    public RowEntity(String rowId, String value, Boolean deleted) {
        this.rowId = rowId;
        this.value = value;
        this.deleted = deleted;
    }
    public RowEntity(String rowId, String value, OperationEnum deleted) {
        this.rowId = rowId;
        this.value = value;
        if (deleted == OperationEnum.DELETE) {
            this.deleted = true;
        } else  {
            this.deleted = false;
        }
    }



    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(RowEntity o) {
        return this.value.compareTo(o.getValue());
    }
}
