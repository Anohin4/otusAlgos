package type;

public class RowEntity {

    private String rowId;
    private String indexValue;
    private Boolean deleted;

    public RowEntity(String indexValue, String rowId) {
        this.rowId = rowId;
        this.indexValue = indexValue;
        this.deleted = false;
    }

    public RowEntity(String indexValue, String rowId, Boolean deleted) {
        this.rowId = rowId;
        this.indexValue = indexValue;
        this.deleted = deleted;
    }

    public RowEntity(String indexValue, String rowId, OperationEnum deleted) {
        this.rowId = rowId;
        this.indexValue = indexValue;
        if (deleted == OperationEnum.DELETE) {
            this.deleted = true;
        } else {
            this.deleted = false;
        }
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "RowEntity " +

                "indexValue='" + indexValue + '\'' + "," + "rowId='" + rowId + "'";
    }
}
