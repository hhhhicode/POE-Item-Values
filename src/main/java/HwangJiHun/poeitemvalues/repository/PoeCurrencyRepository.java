package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class PoeCurrencyRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public PoeCurrencyRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("POE_CURRENCY")
                .usingGeneratedKeyColumns("id");
    }

    public PoeCurrencyDto save(PoeCurrencyDto poeCurrencyDto) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(poeCurrencyDto);

        Number key = jdbcInsert.executeAndReturnKey(param);
        poeCurrencyDto.setId(key.longValue());

        return poeCurrencyDto;
    }

    public List<PoeCurrencyDto> findAll() {
        String sql = "SELECT * FROM POE_CURRENCY";

        return jdbcTemplate.query(sql, getPoeCurrencyDtoRowMapper());
    }

    private static BeanPropertyRowMapper<PoeCurrencyDto> getPoeCurrencyDtoRowMapper() {
        return new BeanPropertyRowMapper<>(PoeCurrencyDto.class);
    }
}
