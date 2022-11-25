package HwangJiHun.poeitemvalues.repository;

public enum OverviewType {
    CURRENCYOVERVIEW("currencyoverview"),
    ITEMOVERVIEW("itemoverview");

    private String overviewTypeName;

    OverviewType(String overviewTypeName) {
        this.overviewTypeName = overviewTypeName;
    }

    public String getApiEndPoint() {
        return "https://poe.ninja/api/data/" + this.overviewTypeName;
    }
}
