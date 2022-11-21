package HwangJiHun.poeitemvalues.repository;

public enum ApiType {

    CURRENCY("Currency"),
    Fragment("Fragment");

    private String typeName;
    ApiType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
