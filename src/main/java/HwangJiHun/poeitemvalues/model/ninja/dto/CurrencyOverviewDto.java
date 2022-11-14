package HwangJiHun.poeitemvalues.model.ninja.dto;

import HwangJiHun.poeitemvalues.model.ninja.MarketPrice;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CurrencyOverviewDto {

    private final String currencyTypeName;
    private final String icon;
    private final String payCurrencyName;
    private final String receiveValue;
    private final String payCurrencyIcon;
    private final String getCurrencyName;
    private final String getCurrencyIcon;
}
