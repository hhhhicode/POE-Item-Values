package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.*;
import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.repository.NinjaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public List<CurrencyOverviewDto> getCurrencyOverview() throws IOException {

        CurrencyOverview currencyOverview = ninjaRepository.getCurrencyOverview();
        Map<Integer, CurrencyDetail> idCurrencyDetailMap = getIdCurrencyDetailMap(currencyOverview.getCurrencyDetails());
        List<CurrencyOverviewDto> currencyOverviewDtoList = new ArrayList<>();

        List<Currency> lines = currencyOverview.getLines();
        for (Currency line : lines) {
            MarketPrice receive = line.getReceive();
            MarketPrice pay = line.getPay();

            /*Currency Overview List*/
            currencyOverviewDtoList.add
                    (
                            getCurrencyOverviewDto
                                    (
                                            getCurrencyNameIconMap(currencyOverview.getCurrencyDetails()),
                                            line,
                                            idCurrencyDetailMap.get(receive.getPayCurrencyId()),
                                            idCurrencyDetailMap.get(receive.getGetCurrencyId()),
                                            pay != null ? getCurrencyDetail(pay, idCurrencyDetailMap, pay.getPayCurrencyId()) : null,
                                            pay != null ? getCurrencyDetail(pay, idCurrencyDetailMap, pay.getGetCurrencyId()) : null,
                                            (int) Math.round(line.getReceiveSparkLine().getTotalChange()),
                                            (int) Math.round(line.getPaySparkLine().getTotalChange()),
                                            receive.getValue(),
                                            pay != null ? 1 / pay.getValue() : null
                                    )
                    );
        }

        return currencyOverviewDtoList;
    }

    private static CurrencyDetail getCurrencyDetail(MarketPrice pay, Map<Integer, CurrencyDetail> idCurrencyDetailMap, Integer pay1) {
        return idCurrencyDetailMap.get(pay1);
    }

    private CurrencyOverviewDto getCurrencyOverviewDto(
            Map<String, String> currencyNameIconMap,
            Currency line,
            CurrencyDetail buyPayCurrencyDetail,
            CurrencyDetail buyGetCurrencyDetail,
            CurrencyDetail sellPayCurrencyDetail,
            CurrencyDetail sellGetCurrencyDetail,
            Integer buySparkLineTotalChange,
            Integer sellSparkLineTotalChange,
            Double buyValue,
            Double sellValue) {
        return new CurrencyOverviewDto
                (
                        /*Name*/
                        line.getCurrencyTypeName(),
                        currencyNameIconMap.get(line.getDetailsId()),
                        buyPayCurrencyDetail.getName(),

                        /*Buying Price*/
                        buyPayCurrencyDetail.getName(),
                        buyValue >= 1000 ?
                                (double) Math.round(buyValue / 1000 * 10) / 10 + "k" :
                                String.valueOf((double) (
                                                Math.round(buyValue * 10) / 10 >= 1 ?
                                                        Math.round(buyValue * 10) / 10 :
                                                        1
                                        )
                                ),
                        buyPayCurrencyDetail.getIcon(),

                        buyGetCurrencyDetail.getName(),
                        buyValue >= 1 ?
                                String.valueOf(1.0) :
                                String.valueOf((double) Math.round(1 / buyValue * 10) / 10),
                        buyGetCurrencyDetail.getIcon(),

                        /*Buying Price Last 7 days*/
                        buySparkLineTotalChange > 0 ?
                                "+" + buySparkLineTotalChange + "%" :
                                buySparkLineTotalChange + "%",

                        /*Selling Price*/
                        sellPayCurrencyDetail != null ? sellPayCurrencyDetail.getName() : null,
                        sellValue != null ?
                                (
                                        sellValue >= 1 ?
                                                String.valueOf(1.0) :
                                                String.valueOf((double) Math.round(1 / sellValue * 10) / 10)
                                ) :
                                null,
                        sellPayCurrencyDetail != null ? sellPayCurrencyDetail.getIcon() : null,

                        sellGetCurrencyDetail != null ? sellGetCurrencyDetail.getName() : null,
                        sellValue != null ?
                                (
                                        sellValue >= 1000 ?
                                                (double) Math.round(sellValue / 1000 * 10) / 10 + "k" :
                                                String.valueOf((double) Math.round(sellValue * 10) / 10)
                                ) :
                                null,
                        sellGetCurrencyDetail != null ? sellGetCurrencyDetail.getIcon() : null,

                        /*Selling Price Last 7 days*/
                        sellSparkLineTotalChange > 0 ?
                                "+" + sellSparkLineTotalChange + "%" :
                                sellSparkLineTotalChange + "%"
                );
    }

    private static void getSvgData(SparkLine buySparkLine) {
        Double svgWidth = 58d;
        Double svgHeight = 20d;
        Double svgSize = 18d;
        List<Double> svgDataList = buySparkLine.getData();
        List<Double> convertSvgDataList = new ArrayList<>();
        List<Double> xList = new ArrayList<>();
        List<Double> yList = new ArrayList<>();
        Double intervalX = svgWidth / svgSize;
        Double valueRange = svgDataList.stream().max(Double::compareTo).get() - svgDataList.stream().min(Double::compareTo).get();
        for (Double svgData : svgDataList) {
            convertSvgDataList.add(svgData * 22 / valueRange);
        }

        for (int i = 1; i < convertSvgDataList.size(); i++) {
            for (int j = 1; j <= 3; j++) {
                Double diffHeight = convertSvgDataList.get(i) - convertSvgDataList.get(i - 1);
                yList.add(convertSvgDataList.get(i - 1) + diffHeight / 3 * j);
            }
        }
        for (int i = 0; i < svgSize; i++) {
            xList.add(i * intervalX);
        }

        //TODO Y에 마이너스 값이 있다. 마이너스가 있으면 안된다.
    }

    private Map<String, String> getCurrencyNameIconMap(List<CurrencyDetail> currencyDetails) {
        Map<String, String> currencyNameIconMap = new HashMap<>();
        for (CurrencyDetail currencyDetail : currencyDetails) {
            currencyNameIconMap.put(
                    currencyDetail.getName()
                            .toLowerCase()
                            .replace(' ', '-')
                            .replace("'", ""),
                    currencyDetail.getIcon()
            );
        }
        return currencyNameIconMap;
    }

    private Map<Integer, CurrencyDetail> getIdCurrencyDetailMap(List<CurrencyDetail> currencyDetails) {
        Map<Integer, CurrencyDetail> currencyDetailMap = new HashMap<>();
        for (CurrencyDetail currencyDetail : currencyDetails) {
            currencyDetailMap.put(currencyDetail.getId(), currencyDetail);
        }
        return currencyDetailMap;
    }
}
