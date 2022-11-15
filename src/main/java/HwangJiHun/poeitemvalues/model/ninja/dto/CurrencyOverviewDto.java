package HwangJiHun.poeitemvalues.model.ninja.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CurrencyOverviewDto {

    private final String currencyTypeName;
    private final String icon;
    private final String payCurrencyName;

    /*Buying Price*/
    private final String buyPayCurrencyName;
    private final String buyPayValue;
    private final String buyPayCurrencyIcon;

    private final String buyGetCurrencyName;
    private final String buyReceiveValue;
    private final String buyGetCurrencyIcon;

    private final String buyTotalChange;

    /*Selling Price*/
    private final String sellPayCurrencyName;
    private final String sellPayValue;
    private final String sellPayCurrencyIcon;

    private final String sellGetCurrencyName;
    private final String sellReceiveValue;
    private final String sellGetCurrencyIcon;

    private final String sellTotalChange;
}
