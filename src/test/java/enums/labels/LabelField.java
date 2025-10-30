package enums.labels;

public enum LabelField {
    NAME("name"),
    COLOR("color");

    private final String value;

    LabelField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
