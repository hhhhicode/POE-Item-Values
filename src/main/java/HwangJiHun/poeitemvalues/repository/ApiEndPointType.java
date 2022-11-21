package HwangJiHun.poeitemvalues.repository;

public enum ApiEndPointType {
    CURRENCYOVERVIEW("currencyoverview"),
    ITEMOVERVIEW("itemoverview");

    private String overviewTypeName;

    ApiEndPointType(String overviewTypeName) {
        this.overviewTypeName = overviewTypeName;
    }

    public String getApiEndPoint() {
        return "https://poe.ninja/api/data/" + this.overviewTypeName;
    }
}
