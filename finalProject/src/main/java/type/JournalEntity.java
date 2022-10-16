package type;

import static index.utils.Utils.COLUMN_SEPARATOR;

public class JournalEntity {

    OperationEnum operation;
    String key;
    String value;

    public JournalEntity(OperationEnum operation, String key, String value) {
        this.operation = operation;
        this.key = key;
        this.value = value;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return operation + COLUMN_SEPARATOR + key + COLUMN_SEPARATOR + value;
    }
}
