package type.tree;

public class RowId {

    private String rowId;
    private boolean isTombStone;

    public RowId(String rowId, boolean isTombStone) {
        this.rowId = rowId;
        this.isTombStone = isTombStone;
    }

    public RowId(String rowId) {
        this.rowId = rowId;
        this.isTombStone = false;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public boolean isTombStone() {
        return isTombStone;
    }

    public void setTombStone(boolean tombStone) {
        isTombStone = tombStone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(rowId);
        if (isTombStone) {
            sb.append("_D");
        }
        return sb.toString();
    }
}
