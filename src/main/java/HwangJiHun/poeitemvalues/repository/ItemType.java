package HwangJiHun.poeitemvalues.repository;

public enum ItemType {

    CURRENCY("Currency"),
    Fragment("Fragment"),
    DIVINATIONCARD("DivinationCard");


    private String typeName;
    ItemType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
