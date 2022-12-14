package HwangJiHun.poeitemvalues.model.ninja.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CurrencyOverviewDto {

    private final String currencyTypeName;
    private final String icon;

    /*Buying Price*/
    private final String buyPayCurrencyName;
    private final String buyPayValue;
    private final String buyPayCurrencyIcon;

    private final String buyGetCurrencyName;
    private final String buyReceiveValue;
    private final String buyGetCurrencyIcon;

    private final String buyChartId;
    private final List<Double> buyChartDataList;
    private final String buyTotalChange;

    /*Selling Price*/
    private final String sellPayCurrencyName;
    private final String sellPayValue;
    private final String sellPayCurrencyIcon;

    private final String sellGetCurrencyName;
    private final String sellReceiveValue;
    private final String sellGetCurrencyIcon;

    private final String sellChartId;
    private final List<Double> sellChartDataList;
    private final String sellTotalChange;
}
