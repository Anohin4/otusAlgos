package type.tree;

import java.util.List;

public class RowEntityForBd {
    public String indexValue;
    public List<RowId> rowIdList;

    public RowEntityForBd(String indexValue, List<RowId> rowIdList) {
        this.indexValue = indexValue;
        this.rowIdList = rowIdList;
    }
}
