package HwangJiHun.poeitemvalues.controller;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import HwangJiHun.poeitemvalues.service.PoeCurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeneralControllerTest {

    @Autowired
    private PoeCurrencyService poeCurrencyService;

    @Test
    void currencyForm() {
        List<PoeCurrencyDto> poeCurrencyDtoList = poeCurrencyService.findAll();
        assertThat(poeCurrencyDtoList).isNotNull().isNotEmpty();
    }

    @Test
    void fragment() {
    }

    @Test
    void divinationcard() {
    }
}