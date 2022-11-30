package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.repository.mybatis.PoeCurrencyDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class NinjaRepositoryTest {

    @Autowired
    private PoeCurrencyDtoMapper poeCurrencyDtoMapper;

    @Test
    public void doubleTest() {
        double maxValue = Double.MAX_VALUE;
        log.info("maxValue = {}", maxValue);
    }
}