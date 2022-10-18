package type;

public enum OperationEnum {
    INSERT("insert"), DELETE("delete");
    private String operation;

    OperationEnum(String operation) {
        this.operation = operation;
    }


}
