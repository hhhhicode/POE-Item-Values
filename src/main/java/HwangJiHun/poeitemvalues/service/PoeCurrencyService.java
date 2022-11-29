package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.dto.CurrencyOverviewDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.CurrencyDetailsDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import HwangJiHun.poeitemvalues.repository.PoeCurrencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PoeCurrencyService {

    private final NinjaService ninjaService;
    private final PoeCurrencyRepository poeCurrencyRepository;
    private final ResourceDbService resourceDbService;

    public PoeCurrencyService(NinjaService ninjaService, PoeCurrencyRepository poeCurrencyRepository, ResourceDbService resourceDbService) {
        this.ninjaService = ninjaService;
        this.poeCurrencyRepository = poeCurrencyRepository;
        this.resourceDbService = resourceDbService;
    }

    @Transactional
    public void saveCurrency(Currency currency, String itemType) {

        PoeCurrencyDto poeCurrencyDto = currencyToPoeCurrencyDto(currency, itemType);
        poeCurrencyRepository.save(poeCurrencyDto);
    }

    public List<PoeCurrencyDto> findAll() {
        return poeCurrencyRepository.findAll();
    }

    public PoeCurrencyDto currencyToPoeCurrencyDto(Currency currency, String itemType) {
        PoeCurrencyDto poeCurrencyDto = new PoeCurrencyDto();
        poeCurrencyDto.setCurrencyTypeName(currency.getCurrencyTypeName());

        if (currency.getPay() != null) {
            poeCurrencyDto.setPayId(currency.getPay().getId());
            poeCurrencyDto.setPayLeagueId(currency.getPay().getLeagueId());
            poeCurrencyDto.setPayPayCurrencyId(currency.getPay().getPayCurrencyId());
            poeCurrencyDto.setPayGetCurrencyId(currency.getPay().getGetCurrencyId());
            poeCurrencyDto.setPaySampleTimeUtc(currency.getPay().getSampleTimeUtc());
            poeCurrencyDto.setPayCount(currency.getPay().getCount());
            poeCurrencyDto.setPayValue(currency.getPay().getValue());
            poeCurrencyDto.setPayDataPointCount(currency.getPay().getDataPointCount());
            poeCurrencyDto.setPayIncludesSecondary(currency.getPay().getIncludesSecondary());
            poeCurrencyDto.setPayListingCount(currency.getPay().getListingCount());
        }

        poeCurrencyDto.setReceiveId(currency.getReceive().getId());
        poeCurrencyDto.setReceiveLeagueId(currency.getReceive().getLeagueId());
        poeCurrencyDto.setReceivePayCurrencyId(currency.getReceive().getPayCurrencyId());
        poeCurrencyDto.setReceiveGetCurrencyId(currency.getReceive().getGetCurrencyId());
        poeCurrencyDto.setReceiveSampleTimeUtc(currency.getReceive().getSampleTimeUtc());
        poeCurrencyDto.setReceiveCount(currency.getReceive().getCount());
        poeCurrencyDto.setReceiveValue(currency.getReceive().getValue());
        poeCurrencyDto.setReceiveDataPointCount(currency.getReceive().getDataPointCount());
        poeCurrencyDto.setReceiveIncludesSecondary(currency.getReceive().getIncludesSecondary());
        poeCurrencyDto.setReceiveListingCount(currency.getReceive().getListingCount());

        if (currency.getPaySparkLine().getData() != null && currency.getPaySparkLine().getData().size() != 0) {
            poeCurrencyDto.setPaySparkLineData0(currency.getPaySparkLine().getData().get(0));
            poeCurrencyDto.setPaySparkLineData1(currency.getPaySparkLine().getData().get(1));
            poeCurrencyDto.setPaySparkLineData2(currency.getPaySparkLine().getData().get(2));
            poeCurrencyDto.setPaySparkLineData3(currency.getPaySparkLine().getData().get(3));
            poeCurrencyDto.setPaySparkLineData4(currency.getPaySparkLine().getData().get(4));
            poeCurrencyDto.setPaySparkLineData5(currency.getPaySparkLine().getData().get(5));
            poeCurrencyDto.setPaySparkLineData6(currency.getPaySparkLine().getData().get(6));
        }
        poeCurrencyDto.setPaySparkLineTotalChange(currency.getPaySparkLine().getTotalChange());

        if (currency.getReceiveSparkLine().getData() != null && currency.getReceiveSparkLine().getData().size() != 0) {
            poeCurrencyDto.setReceiveSparkLineData0(currency.getReceiveSparkLine().getData().get(0));
            poeCurrencyDto.setReceiveSparkLineData1(currency.getReceiveSparkLine().getData().get(1));
            poeCurrencyDto.setReceiveSparkLineData2(currency.getReceiveSparkLine().getData().get(2));
            poeCurrencyDto.setReceiveSparkLineData3(currency.getReceiveSparkLine().getData().get(3));
            poeCurrencyDto.setReceiveSparkLineData4(currency.getReceiveSparkLine().getData().get(4));
            poeCurrencyDto.setReceiveSparkLineData5(currency.getReceiveSparkLine().getData().get(5));
            poeCurrencyDto.setReceiveSparkLineData6(currency.getReceiveSparkLine().getData().get(6));
        }
        poeCurrencyDto.setReceiveSparkLineTotalChange(currency.getReceiveSparkLine().getTotalChange());

        poeCurrencyDto.setChaosEquivalent(currency.getChaosEquivalent());
        poeCurrencyDto.setDetailsId(currency.getDetailsId());
        poeCurrencyDto.setItemType(itemType);

        return poeCurrencyDto;
    }

    public List<CurrencyOverviewDto> poeCurrencyDtoListToCurrencyOverviewDtoList(List<PoeCurrencyDto> poeCurrencyDtoList) {

        List<CurrencyOverviewDto> currencyOverviewDtoList = new ArrayList<>();
        List<CurrencyDetailsDto> currencyDetailsDtoList = resourceDbService.findAll();

        for (PoeCurrencyDto poeCurrencyDto : poeCurrencyDtoList) {
            CurrencyDetailsDto currencyDetails = getTargetItemCurrencyDetails(currencyDetailsDtoList, poeCurrencyDto);

            CurrencyDetailsDto buyPayCurrencyDetails = getCurrencyDetails(currencyDetailsDtoList, poeCurrencyDto.getReceivePayCurrencyId());
            CurrencyDetailsDto buyGetCurrencyDetails = getCurrencyDetails(currencyDetailsDtoList, poeCurrencyDto.getReceiveGetCurrencyId());

            String buyPayValue = getBuyPayValue(poeCurrencyDto.getReceiveValue());
            String buyReceiveValue = getBuyReceiveValue(poeCurrencyDto.getReceiveValue());

            String buyChartId = "buy_" + poeCurrencyDto.getCurrencyTypeName();
            List<Double> buyChartDataList = Arrays.asList
                    (
                            poeCurrencyDto.getReceiveSparkLineData0(),
                            poeCurrencyDto.getReceiveSparkLineData1(),
                            poeCurrencyDto.getReceiveSparkLineData2(),
                            poeCurrencyDto.getReceiveSparkLineData3(),
                            poeCurrencyDto.getReceiveSparkLineData4(),
                            poeCurrencyDto.getReceiveSparkLineData5(),
                            poeCurrencyDto.getReceiveSparkLineData6()
                    );
            String buyTotalChange = getTotalChange(poeCurrencyDto.getReceiveSparkLineTotalChange());

            CurrencyDetailsDto sellPayCurrencyDetails = getCurrencyDetails(currencyDetailsDtoList, poeCurrencyDto.getPayPayCurrencyId());
            String sellPayValue = getSellPayValue(poeCurrencyDto.getPayValue());
            CurrencyDetailsDto sellGetCurrencyDetails = getCurrencyDetails(currencyDetailsDtoList, poeCurrencyDto.getPayGetCurrencyId());
            String sellReceiveValue = getSellReceiveValue(poeCurrencyDto.getPayValue());

            String sellChartId = "sell_" + poeCurrencyDto.getCurrencyTypeName();
            List<Double> sellChartDataList = Arrays.asList
                    (
                            poeCurrencyDto.getPaySparkLineData0(),
                            poeCurrencyDto.getPaySparkLineData1(),
                            poeCurrencyDto.getPaySparkLineData2(),
                            poeCurrencyDto.getPaySparkLineData3(),
                            poeCurrencyDto.getPaySparkLineData4(),
                            poeCurrencyDto.getPaySparkLineData5(),
                            poeCurrencyDto.getPaySparkLineData6()
                    );
            String sellTotalChange = getTotalChange(poeCurrencyDto.getPaySparkLineTotalChange());

            CurrencyOverviewDto currencyOverviewDto = getCurrencyOverviewDto
                    (
                            poeCurrencyDto,
                            currencyDetails,
                            buyPayCurrencyDetails,
                            buyGetCurrencyDetails,
                            buyPayValue,
                            buyReceiveValue,
                            buyChartId,
                            buyChartDataList,
                            buyTotalChange,
                            sellPayCurrencyDetails,
                            sellPayValue,
                            sellGetCurrencyDetails,
                            sellReceiveValue,
                            sellChartId,
                            sellChartDataList,
                            sellTotalChange
                    );

            currencyOverviewDtoList.add(currencyOverviewDto);
        }

        return currencyOverviewDtoList;
    }

    private static String getSellReceiveValue(Double sellReceiveValue) {
        return sellReceiveValue != null ?
                (
                        sellReceiveValue >= 1000 ?
                                (double) Math.round(sellReceiveValue / 1000 * 10) / 10 + "k" :
                                String.valueOf((double) Math.round(sellReceiveValue * 10) / 10)
                ) :
                null;
    }

    private static String getSellPayValue(Double  sellPayValue) {
        return sellPayValue != null ?
                (
                        sellPayValue >= 1 ?
                                String.valueOf(1.0) :
                                String.valueOf((double) Math.round(1 / sellPayValue * 10) / 10)
                ) :
                null;
    }

    private static String getTotalChange(Double totalChange) {
        return totalChange > 0 ?
                "+" + totalChange + "%" :
                totalChange + "%";
    }

    private static String getBuyReceiveValue(Double buyReceiveValue) {
        return buyReceiveValue >= 1 ?
                String.valueOf(1.0) :
                String.valueOf((double) Math.round(1 / buyReceiveValue * 10) / 10);
    }

    private static String getBuyPayValue(Double receiveValue) {
        return receiveValue >= 1000 ?
                (double) Math.round(receiveValue / 1000 * 10) / 10 + "k" :
                String.valueOf(
                        receiveValue >= 1.0 ?
                                (double) Math.round(receiveValue * 10) / 10 :
                                1.0
                );
    }

    private static CurrencyDetailsDto getCurrencyDetails(List<CurrencyDetailsDto> currencyDetailsDtoList, Integer poeCurrencyDto) {
        CurrencyDetailsDto buyPayCurrencyDetails = currencyDetailsDtoList.stream()
                .filter(o -> o.getId() == poeCurrencyDto)
                .findAny()
                .orElse(null);
        return buyPayCurrencyDetails;
    }

    private static CurrencyDetailsDto getTargetItemCurrencyDetails(List<CurrencyDetailsDto> currencyDetailsDtoList, PoeCurrencyDto poeCurrencyDto) {
        CurrencyDetailsDto currencyDetails = currencyDetailsDtoList.stream()
                .filter(o -> o.getName() == poeCurrencyDto.getCurrencyTypeName())
                .findAny()
                .orElse(null);
        return currencyDetails;
    }

    private static CurrencyOverviewDto getCurrencyOverviewDto(PoeCurrencyDto poeCurrencyDto, CurrencyDetailsDto currencyDetails, CurrencyDetailsDto buyPayCurrencyDetails, CurrencyDetailsDto buyGetCurrencyDetails, String buyPayValue, String buyReceiveValue, String buyChartId, List<Double> buyChartDataList, String buyTotalChange, CurrencyDetailsDto sellPayCurrencyDetails, String sellPayValue, CurrencyDetailsDto sellGetCurrencyDetails, String sellReceiveValue, String sellChartId, List<Double> sellChartDataList, String sellTotalChange) {
        CurrencyOverviewDto currencyOverviewDto = new CurrencyOverviewDto
                (
                        poeCurrencyDto.getCurrencyTypeName(),
                        currencyDetails.getIcon(),

                        buyPayCurrencyDetails.getName(),
                        buyPayValue,
                        buyPayCurrencyDetails.getIcon(),

                        buyGetCurrencyDetails.getName(),
                        buyReceiveValue,
                        buyGetCurrencyDetails.getIcon(),

                        buyChartId,
                        buyChartDataList,
                        buyTotalChange,

                        sellPayCurrencyDetails != null ? sellPayCurrencyDetails.getName() : null,
                        sellPayValue,
                        sellPayCurrencyDetails != null ? sellPayCurrencyDetails.getIcon() : null,

                        sellGetCurrencyDetails.getName(),
                        sellReceiveValue,
                        sellGetCurrencyDetails.getIcon(),

                        sellChartId,
                        sellChartDataList,
                        sellTotalChange
                );
        return currencyOverviewDto;
    }
}
