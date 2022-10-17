package type.tree;

public class RowId {

    private String rowId;
    private boolean isTombStone;

    public RowId(String rowId, boolean isTombStone) {
        this.rowId = rowId;
        this.isTombStone = isTombStone;
    }

    public RowId(String rowId) {
        if (rowId.endsWith("_D")) {
            this.isTombStone = true;
            this.rowId = rowId.substring(0, rowId.length() - 2);
        } else {
            this.isTombStone = false;
            this.rowId = rowId;
        }

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
