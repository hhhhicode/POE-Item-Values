package HwangJiHun.poeitemvalues.scheduler;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.CurrencyOverview;
import HwangJiHun.poeitemvalues.repository.ItemType;
import HwangJiHun.poeitemvalues.repository.OverviewType;
import HwangJiHun.poeitemvalues.service.H2DataBaseService;
import HwangJiHun.poeitemvalues.service.NinjaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SchedulerTest {

    @Autowired
    private NinjaService ninjaService;

    @Autowired
    private H2DataBaseService h2DataBaseService;

    @Test
    @Transactional
    @DisplayName("DB 저장 확인")
    void savePoeItemValues() throws IOException {
        CurrencyOverview currencyOverview = ninjaService.getOverview(OverviewType.CURRENCYOVERVIEW.getApiEndPoint(), ItemType.CURRENCY.getTypeName());
        assertThat(currencyOverview).isNotNull();

        List<Currency> lines = currencyOverview.getLines();
        for (Currency line : lines) {
            h2DataBaseService.currencySave(line);
        }
        h2DataBaseService.findAll().size();
        assertThat(h2DataBaseService.findAll().size()).isEqualTo(currencyOverview.getLines().size());
    }
}