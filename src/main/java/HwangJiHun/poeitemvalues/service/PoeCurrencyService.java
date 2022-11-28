package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.repository.OverviewType;
import HwangJiHun.poeitemvalues.repository.PoeCurrencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class PoeCurrencyService {

    private final NinjaService ninjaService;
    private final PoeCurrencyRepository poeCurrencyRepository;

    public PoeCurrencyService(NinjaService ninjaService, PoeCurrencyRepository poeCurrencyRepository) {
        this.ninjaService = ninjaService;
        this.poeCurrencyRepository = poeCurrencyRepository;
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

        poeCurrencyDto.setPaySparkLineData0(currency.getPaySparkLine().getData().get(0));
        poeCurrencyDto.setPaySparkLineData1(currency.getPaySparkLine().getData().get(1));
        poeCurrencyDto.setPaySparkLineData2(currency.getPaySparkLine().getData().get(2));
        poeCurrencyDto.setPaySparkLineData3(currency.getPaySparkLine().getData().get(3));
        poeCurrencyDto.setPaySparkLineData4(currency.getPaySparkLine().getData().get(4));
        poeCurrencyDto.setPaySparkLineData5(currency.getPaySparkLine().getData().get(5));
        poeCurrencyDto.setPaySparkLineData6(currency.getPaySparkLine().getData().get(6));
        poeCurrencyDto.setPaySparkLineTotalChange(currency.getPaySparkLine().getTotalChange());

        poeCurrencyDto.setReceiveSparkLineData0(currency.getReceiveSparkLine().getData().get(0));
        poeCurrencyDto.setReceiveSparkLineData1(currency.getReceiveSparkLine().getData().get(1));
        poeCurrencyDto.setReceiveSparkLineData2(currency.getReceiveSparkLine().getData().get(2));
        poeCurrencyDto.setReceiveSparkLineData3(currency.getReceiveSparkLine().getData().get(3));
        poeCurrencyDto.setReceiveSparkLineData4(currency.getReceiveSparkLine().getData().get(4));
        poeCurrencyDto.setReceiveSparkLineData5(currency.getReceiveSparkLine().getData().get(5));
        poeCurrencyDto.setReceiveSparkLineData6(currency.getReceiveSparkLine().getData().get(6));
        poeCurrencyDto.setReceiveSparkLineTotalChange(currency.getReceiveSparkLine().getTotalChange());

        poeCurrencyDto.setChaosEquivalent(currency.getChaosEquivalent());
        poeCurrencyDto.setDetailsId(currency.getDetailsId());
        poeCurrencyDto.setItemType(itemType);

        return poeCurrencyDto;
    }
}
