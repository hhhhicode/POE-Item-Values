package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.CurrencyDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@SpringBootTest
class CurrencyDetailsRepositoryTest {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    CurrencyDetailsRepositoryTest(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Test
    void findAll() {

        String sql = "SELECT * FROM CURRENCY_DETAILS";
        List<CurrencyDetailsDto> currencyDetailsDtoList = jdbcTemplate.query(sql, getBeanPropertyRowMapper());
        System.out.println("currencyDetailsDtoList = " + currencyDetailsDtoList);
    }

    private static RowMapper<CurrencyDetailsDto> getBeanPropertyRowMapper() {
        return BeanPropertyRowMapper.newInstance(CurrencyDetailsDto.class);
    }
}