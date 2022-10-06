package index;

import static index.utils.Utils.COLUMN_SEPARATOR;

public class RowEntity {
    private String rowId;
    private String value;
    private Boolean deleted;

    public RowEntity(String indexValue, String rowId) {
        this.rowId = rowId;
        this.value = indexValue;
        this.deleted = false;
    }

    public RowEntity(String indexValue, String rowId, Boolean deleted) {
        this.rowId = rowId;
        this.value = indexValue;
        this.deleted = deleted;
    }
    public RowEntity(String indexValue, String rowId, OperationEnum deleted) {
        this.rowId = rowId;
        this.value = indexValue;
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

    public Boolean getDeleted() {
        return deleted;
    }
}
