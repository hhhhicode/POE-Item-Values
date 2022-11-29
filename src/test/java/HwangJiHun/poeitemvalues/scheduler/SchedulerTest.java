package HwangJiHun.poeitemvalues.scheduler;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.CurrencyDetail;
import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.CurrencyDetailsDto;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.repository.OverviewType;
import HwangJiHun.poeitemvalues.service.H2DataBaseService;
import HwangJiHun.poeitemvalues.service.NinjaService;
import HwangJiHun.poeitemvalues.service.PoeCurrencyService;
import HwangJiHun.poeitemvalues.service.ResourceDbService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class SchedulerTest {

    @Autowired
    private NinjaService ninjaService;
    @Autowired
    private H2DataBaseService h2DataBaseService;
    @Autowired
    private PoeCurrencyService poeCurrencyService;
    @Autowired
    private ResourceDbService resourceDbService;

    @Test
    @Transactional
    @DisplayName("DB Currency 저장 확인")
    void savePoeCurrencyValues() throws IOException {
        CurrencyOverview currencyOverview = ninjaService.getOverview(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
        assertThat(currencyOverview).isNotNull();

        List<Currency> lines = currencyOverview.getLines();
        for (Currency line : lines) {
            h2DataBaseService.currencySave(line, ItemType.CURRENCY.getTypeName());
        }
        h2DataBaseService.findAll().size();
        assertThat(h2DataBaseService.findAll().size()).isEqualTo(currencyOverview.getLines().size());
    }

    @Test
    @Transactional
    @DisplayName("DB Fragment 저장 확인")
    void savePoeFragmentValues() throws IOException {
        CurrencyOverview currencyOverview = ninjaService.getOverview(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.Fragment.getTypeName());
        assertThat(currencyOverview).isNotNull();

        List<Currency> lines = currencyOverview.getLines();
        for (Currency line : lines) {
            h2DataBaseService.currencySave(line, ItemType.Fragment.getTypeName());
        }

        int findByItemSize = h2DataBaseService.findByItemType(ItemType.Fragment.getTypeName()).size();
        assertThat(findByItemSize).isEqualTo(currencyOverview.getLines().size());
    }

    @Test
    @Transactional
    @DisplayName("Poe Currency DB 저장")
    void savePoeCurrencyTest() throws IOException {
        CurrencyOverview overview = ninjaService.getOverview(OverviewType.ITEMOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
        for (Currency currency : overview.getLines()) {
            poeCurrencyService.saveCurrency(currency, ItemType.CURRENCY.getTypeName());
        }
        int dbSize = poeCurrencyService.findAll().size();
        int overviewSize = overview.getLines().size();

        assertThat(dbSize).isEqualTo(overviewSize);
    }

    @Test
    @DisplayName("Poe Currency & Details DB 저장")
    void saveCurrencyAndDetailsTest() throws IOException {
        CurrencyOverview currencyOverview = ninjaService.getOverview(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
        List<Currency> currencyList = currencyOverview.getLines();
        for (Currency currency : currencyList) {
            poeCurrencyService.saveCurrency(currency, ItemType.CURRENCY.getTypeName());
        }
        log.info("{} Scheduler Complete !!", Thread.currentThread().getStackTrace()[1].getMethodName());

        int deleteCount = resourceDbService.deleteAllCurrencyDetails();
        log.info("{} CurrencyDetails delete Complete !! {}", Thread.currentThread().getStackTrace()[1].getMethodName(), deleteCount);
        int count = 0;
        List<CurrencyDetail> currencyDetails = currencyOverview.getCurrencyDetails();
        for (CurrencyDetail currencyDetail : currencyDetails) {
            CurrencyDetailsDto currencyDetailsDto = new CurrencyDetailsDto(
                    currencyDetail.getId(),
                    currencyDetail.getIcon(),
                    currencyDetail.getName(),
                    currencyDetail.getTradeId()
            );
            count += resourceDbService.saveCurrencyDetails(currencyDetailsDto);
        }
        log.info("{} Scheduler Complete !! {}/{}", Thread.currentThread().getStackTrace()[1].getMethodName(), count, currencyDetails.size());
    }
}