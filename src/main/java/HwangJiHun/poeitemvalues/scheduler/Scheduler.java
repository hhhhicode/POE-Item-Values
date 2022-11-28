package HwangJiHun.poeitemvalues.scheduler;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.repository.OverviewType;
import HwangJiHun.poeitemvalues.service.H2DataBaseService;
import HwangJiHun.poeitemvalues.service.NinjaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class Scheduler {

    private final NinjaService ninjaService;
    private final H2DataBaseService h2DataBaseService;

    @Autowired
    public Scheduler(NinjaService ninjaService, H2DataBaseService h2DataBaseService) {
        this.ninjaService = ninjaService;
        this.h2DataBaseService = h2DataBaseService;
    }

    /**
     * <p>
     * <b>매 시간</b> Poe Item Values를
     * <br>H2 DB에 <b>저장</b>하는 Scheduler.
     * </p>
     * <p>0초 0분 *시 *일 *월 *요일 [*년]</p>
     *
     * @throws IOException
     */
    @Scheduled(cron = "${my.item.save.cron}")
    public void savePoeCurrencyValues() throws IOException {

        CurrencyOverview currencyOverview = ninjaService.getOverview(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
        List<Currency> lines = currencyOverview.getLines();
        for (Currency line : lines) {
            h2DataBaseService.currencySave(line, ItemType.CURRENCY.getTypeName());
        }

        log.info("{} Scheduler Complete !!", Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Scheduled(cron = "${my.item.save.cron}")
    public void savePoeFragmentValues() throws IOException {
        CurrencyOverview fragmentOverview = ninjaService.getOverview(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.Fragment.getTypeName());
        for (Currency line : fragmentOverview.getLines()) {
            h2DataBaseService.currencySave(line, ItemType.Fragment.getTypeName());
        }

        log.info("{} Scheduler Complete !!", Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
