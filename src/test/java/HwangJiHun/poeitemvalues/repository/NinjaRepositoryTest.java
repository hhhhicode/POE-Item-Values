package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.UpdateParamDto;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
import HwangJiHun.poeitemvalues.repository.mybatis.UpdateParamDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class NinjaRepositoryTest {

    @Autowired
    private UpdateParamDtoMapper updateParamDtoMapper;

    @Test
    public void doubleTest() {
        double maxValue = Double.MAX_VALUE;
        log.info("maxValue = {}", maxValue);
    }

    @Test
    @DisplayName("MyBatis Test")
    public void selectByCondTest() {
        double topValue = 300d;

        ItemSearchCond cond = new ItemSearchCond();
        cond.setBottomPrice(topValue);

        List<UpdateParamDto> findCurrency = updateParamDtoMapper.findByCond(cond);
        UpdateParamDto min = findCurrency.stream().min(Comparator.comparingDouble(o -> o.getReceiveValue())).orElse(null);
        assertThat(min).isNotNull();
        assertThat(min.getReceiveValue()).isGreaterThanOrEqualTo(topValue);
    }
}