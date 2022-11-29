package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.CurrencyDetailsDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CurrencyDetailsRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public CurrencyDetailsRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("CURRENCY_DETAILS");
    }

    public List<CurrencyDetailsDto> findAll() {
        String sql = "SELECT * FROM CURRENCY_DETAILS";
        return jdbcTemplate.query(sql, getBeanPropertyRowMapper());
    }

    private static BeanPropertyRowMapper getBeanPropertyRowMapper() {
        return new BeanPropertyRowMapper(CurrencyDetailsDto.class);
    }

    public int save(CurrencyDetailsDto currencyDetailsDto) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(currencyDetailsDto);
        return jdbcInsert.execute(param);
    }

    public int deleteAll() {
        String sql = "DELETE FROM CURRENCY_DETAILS";

        return jdbcTemplate.update(sql, new MapSqlParameterSource());
    }
}
