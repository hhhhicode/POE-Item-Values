package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.CurrencyDetailsDto;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
import HwangJiHun.poeitemvalues.repository.mybatis.PoeCurrencyDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@DisplayName("DB 에서 Currency 데이터를 다루는 Service")
class PoeCurrencyServiceTest {

    private final PoeCurrencyService poeCurrencyService;
    private final ResourceDbService resourceDbService;
    private final PoeCurrencyDtoMapper poeCurrencyDtoMapper;

    @Autowired
    PoeCurrencyServiceTest(PoeCurrencyService poeCurrencyService, ResourceDbService resourceDbService, PoeCurrencyDtoMapper poeCurrencyDtoMapper) {
        this.poeCurrencyService = poeCurrencyService;
        this.resourceDbService = resourceDbService;
        this.poeCurrencyDtoMapper = poeCurrencyDtoMapper;
    }

    @Test
    @DisplayName("DB에서 Currency를 잘 가져오는가")
    void findAll() {
        List<PoeCurrencyDto> poeCurrencyDtoList = poeCurrencyService.findAll();
        assertThat(poeCurrencyDtoList).isNotNull().isNotEmpty();
        for (PoeCurrencyDto poeCurrencyDto : poeCurrencyDtoList) {
            List<Double> asList = Arrays.asList(
                    poeCurrencyDto.getReceiveSparkLineData0(),
                    poeCurrencyDto.getReceiveSparkLineData1(),
                    poeCurrencyDto.getReceiveSparkLineData2(),
                    poeCurrencyDto.getReceiveSparkLineData3(),
                    poeCurrencyDto.getReceiveSparkLineData4(),
                    poeCurrencyDto.getReceiveSparkLineData5(),
                    poeCurrencyDto.getReceiveSparkLineData6()
            );
            log.info("name = {}, dataList = {}", poeCurrencyDto.getCurrencyTypeName(), asList);
        }
    }

    @Test
    @DisplayName("각 Currency에 맞는 CurrencyDetails를 가져 오는가")
    void poeCurrencyDtoListToCurrencyOverviewDtoList() {
        List<PoeCurrencyDto> poeCurrencyDtoList = poeCurrencyService.findAll();
        assertThat(poeCurrencyDtoList).isNotNull().isNotEmpty();
        List<CurrencyDetailsDto> currencyDetailsDtoList = resourceDbService.findAll();
        assertThat(currencyDetailsDtoList).isNotNull().isNotEmpty();

        for (PoeCurrencyDto poeCurrencyDto : poeCurrencyDtoList) {
            String currencyTypeName = poeCurrencyDto.getCurrencyTypeName();
            System.out.println("currencyTypeName = " + currencyTypeName);
            CurrencyDetailsDto currencyDetails = getTargetItemCurrencyDetails(currencyDetailsDtoList, poeCurrencyDto);
            assertThat(currencyDetails).isNotNull();
        }
    }

    @Test
    @DisplayName("findCond Test")
    void findCondTest() {

        ItemSearchCond itemSearchCond = new ItemSearchCond();
        itemSearchCond.setItemName("Mi");
        itemSearchCond.setBottomPrice(0d);
        itemSearchCond.setTopPrice(30000d);

        List<PoeCurrencyDto> byCond = poeCurrencyDtoMapper.findByCond(itemSearchCond);
        for (PoeCurrencyDto poeCurrencyDto : byCond) {
            log.info("{} : value = {}, % = {}",
                    poeCurrencyDto.getCurrencyTypeName(),
                    poeCurrencyDto.getReceiveValue(),
                    poeCurrencyDto.getReceiveSparkLineTotalChange());
        }
    }

    private static CurrencyDetailsDto getTargetItemCurrencyDetails(List<CurrencyDetailsDto> currencyDetailsDtoList, PoeCurrencyDto poeCurrencyDto) {
        CurrencyDetailsDto currencyDetails = currencyDetailsDtoList.stream()
                .filter(o -> o.getName().equals(poeCurrencyDto.getCurrencyTypeName()))
                .findAny()
                .orElse(null);
        String name = currencyDetails.getName();
        System.out.println("nameDetails = " + name);
        System.out.println("nameCurrency = " + poeCurrencyDto.getCurrencyTypeName());
        return currencyDetails;
    }
}