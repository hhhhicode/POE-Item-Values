package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.*;
import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.dto.DivinationCardOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.CardsDataListDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.TotalChange5Currency;
import HwangJiHun.poeitemvalues.repository.ApiType;
import HwangJiHun.poeitemvalues.repository.NinjaRepository;
import HwangJiHun.poeitemvalues.repository.ApiEndPointType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public List<CurrencyOverviewDto> getCurrencyOverview() throws IOException {

        CurrencyOverview currencyOverview = ninjaRepository.getCurrencyOverview(ApiEndPointType.CURRENCYOVERVIEW.getApiEndPoint(), ApiType.CURRENCY.getTypeName());
        List<CurrencyOverviewDto> currencyOverviewDtoList = getCurrencyOverviewDtoList(currencyOverview);

        return currencyOverviewDtoList;
    }

    public List<CurrencyOverviewDto> getFragmentOverview() throws IOException {
        CurrencyOverview fragmentCurrencyOverview = ninjaRepository.getCurrencyOverview(ApiEndPointType.CURRENCYOVERVIEW.getApiEndPoint(), ApiType.Fragment.getTypeName());
        List<CurrencyOverviewDto> fragmentOverviewDtoList = getCurrencyOverviewDtoList(fragmentCurrencyOverview);

        return fragmentOverviewDtoList;
    }

    public List<DivinationCardOverviewDto> getDivinationCardOverview() throws IOException {
        DivinationCardOverview divinationCardOverview = ninjaRepository.getDivinationCardOverview(ApiEndPointType.ITEMOVERVIEW.getApiEndPoint(), ApiType.DIVINATIONCARD.getTypeName());
        List<DivinationCardOverviewDto> divinationCardOverviewDtoList = getDivinationCardOverviewDtoList(divinationCardOverview);
        return divinationCardOverviewDtoList;
    }

    private List<CurrencyOverviewDto> getCurrencyOverviewDtoList(CurrencyOverview currencyOverview) {
        List<CurrencyOverviewDto> currencyOverviewDtoList = new ArrayList<>();
        Map<Integer, CurrencyDetail> idCurrencyDetailMap = getIdCurrencyDetailMap(currencyOverview.getCurrencyDetails());

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

    private List<DivinationCardOverviewDto> getDivinationCardOverviewDtoList(DivinationCardOverview divinationCardOverview) {
        List<DivinationCardOverviewDto> divinationCardOverviewDtoList = new ArrayList<>();

        List<DivinationCard> lines = divinationCardOverview.getLines();
        for (DivinationCard line : lines) {

            /*Divination Card Overview List*/
            divinationCardOverviewDtoList.add
                    (
                            new DivinationCardOverviewDto
                                    (
                                            line.getName(),
                                            line.getIcon(),
                                            line.getStackSize(),
                                            line.getDivineValue() > 2 ? (double) Math.round(line.getDivineValue()*10)/10 : (double) Math.round(line.getChaosValue()*10)/10,
                                            line.getSparkline().getData(),
                                            (int) Math.round(line.getSparkline().getTotalChange()) > 0 ? "+" + (int) Math.round(line.getSparkline().getTotalChange()) + "%"
                                            : (int) Math.round(line.getSparkline().getTotalChange()) + "%",
                                            line.getListingCount()
                                    )
                    );
        }
        return divinationCardOverviewDtoList;
    }


    public CardsDataListDto getTop5CardsData() throws IOException {
        CurrencyOverview currencyOverview = ninjaRepository.getCurrencyOverview(ApiEndPointType.CURRENCYOVERVIEW.getApiEndPoint(), ApiType.CURRENCY.getTypeName());
        List<CurrencyDetail> currencyDetails = currencyOverview.getCurrencyDetails();


        Map<String, String> currencyNameIconMap = getCurrencyNameIconMap(currencyDetails);
        Map<Integer, CurrencyDetail> idCurrencyDetailMap = getIdCurrencyDetailMap(currencyDetails);

        List<Currency> lines = currencyOverview.getLines();
        lines.sort(Comparator.comparing(currency -> currency.getReceiveSparkLine().getTotalChange()));
        List<TotalChange5Currency> totalChangeBottom5CurrencyList = getTotalChange5Currencies(lines, currencyNameIconMap, idCurrencyDetailMap);
        lines.sort(Comparator.comparing((currency -> ((Currency) currency).getReceiveSparkLine().getTotalChange())).reversed());
        List<TotalChange5Currency> totalChangeTop5CurrencyList = getTotalChange5Currencies(lines, currencyNameIconMap, idCurrencyDetailMap);

        CardsDataListDto cardsDataListDto = new CardsDataListDto();
        cardsDataListDto.setCurrencyTop5Cards(totalChangeTop5CurrencyList);
        cardsDataListDto.setCurrencyBottom5Cards(totalChangeBottom5CurrencyList);

        return cardsDataListDto;
    }

    private static List<TotalChange5Currency> getTotalChange5Currencies(List<Currency> lines, Map<String, String> currencyNameIconMap, Map<Integer, CurrencyDetail> idCurrencyDetailMap) {
        List<TotalChange5Currency> totalChangeTop5CurrencyList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MarketPrice receive = lines.get(i).getReceive();

            String currencyTypeName = lines.get(i).getCurrencyTypeName();
            String icon = currencyNameIconMap.get(lines.get(i).getDetailsId());
            CurrencyDetail buyPayCurrencyDetail = idCurrencyDetailMap.get(receive.getPayCurrencyId());
            CurrencyDetail buyGetCurrencyDetail = idCurrencyDetailMap.get(receive.getGetCurrencyId());
            String buyPayValue = receive.getValue() >= 1000 ?
                    (double) Math.round(receive.getValue() / 1000 * 10) / 10 + "k" :
                    String.valueOf((double) (
                                    receive.getValue() >= 1 ?
                                            (double) Math.round(receive.getValue() * 10) / 10 :
                                            1
                            )
                    );
            String buyReceiveValue = receive.getValue() >= 1 ?
                    String.valueOf(1.0) :
                    String.valueOf((double) Math.round(1 / receive.getValue() * 10) / 10);

            SparkLine receiveSparkLine = lines.get(i).getReceiveSparkLine();
            int buyTotalChange = (int) Math.round(receiveSparkLine.getTotalChange());

            totalChangeTop5CurrencyList.add(new TotalChange5Currency
                    (
                            currencyTypeName,
                            icon,

                            buyPayCurrencyDetail.getName(),
                            buyPayValue,
                            buyPayCurrencyDetail.getIcon(),

                            buyGetCurrencyDetail.getName(),
                            buyReceiveValue,
                            buyGetCurrencyDetail.getIcon(),

                            "buy " + currencyTypeName,
                            receiveSparkLine.getData(),
                            buyTotalChange > 0 ?
                                    "+" + buyTotalChange + "%" :
                                    buyTotalChange + "%"
                    )
            );
        }
        return totalChangeTop5CurrencyList;
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

        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto
                (
                        /*Name*/
                        line.getCurrencyTypeName(),
                        currencyNameIconMap.get(line.getDetailsId()),

                        /*Buying Price*/
                        buyPayCurrencyDetail.getName(),
                        buyValue >= 1000 ?
                                (double) Math.round(buyValue / 1000 * 10) / 10 + "k" :
                                String.valueOf((double) (
                                                buyValue >= 1 ?
                                                        (double) Math.round(buyValue * 10) / 10 :
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
                        "buy " + line.getCurrencyTypeName(),
                        line.getReceiveSparkLine().getData(),
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
                        "sell " + line.getCurrencyTypeName(),
                        line.getPaySparkLine().getData(),
                        sellSparkLineTotalChange > 0 ?
                                "+" + sellSparkLineTotalChange + "%" :
                                sellSparkLineTotalChange + "%"
                );
        return currencyOverviewDto;
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
