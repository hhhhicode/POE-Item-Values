package HwangJiHun.poeitemvalues.repository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
class PoeCurrencyRepositoryTest {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    PoeCurrencyRepositoryTest(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("test");
    }

    @Test
    void save() {
        TestListClass testListClass = new TestListClass();
        testListClass.setMap(new HashMap<>());
        Map<String, Integer> map = testListClass.getMap();
        map.put("data0", 0);
        map.put("data1", 1);
        map.put("data2", 2);

        SqlParameterSource param = new MapSqlParameterSource(map);
        jdbcInsert.execute(param);
    }

    @Data
    static class TestListClass {
        Map<String, Integer> map;
    }
}