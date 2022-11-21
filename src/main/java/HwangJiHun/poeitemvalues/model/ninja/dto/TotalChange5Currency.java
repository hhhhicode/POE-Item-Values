package HwangJiHun.poeitemvalues.model.ninja.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TotalChange5Currency {

    private final String currencyTypeName;
    private final String icon;

    private final String buyPayCurrencyName;
    private final String buyPayValue;
    private final String buyPayCurrencyIcon;

    private final String buyGetCurrencyName;
    private final String buyReceiveValue;
    private final String buyGetCurrencyIcon;

    private final String buyChartId;
    private final List<Double> buyChartDataList;
    private final String buyTotalChange;
}
