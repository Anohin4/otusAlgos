package index;

public enum OperationEnum {
    INSERT("INSERT"), DELETE("DELETE");
    private String operation;

    OperationEnum(String operation) {
        this.operation = operation;
    }


}
