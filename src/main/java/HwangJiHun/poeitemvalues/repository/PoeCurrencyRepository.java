package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.dto.database.PoeCurrencyDto;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
import HwangJiHun.poeitemvalues.repository.mybatis.PoeCurrencyDtoMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
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
    private final PoeCurrencyDtoMapper poeCurrencyDtoMapper;

    public PoeCurrencyRepository(DataSource dataSource, PoeCurrencyDtoMapper mapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("POE_CURRENCY")
                .usingGeneratedKeyColumns("id");
        this.poeCurrencyDtoMapper = mapper;
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

    public List<PoeCurrencyDto> findCond(ItemSearchCond cond) {
        return poeCurrencyDtoMapper.findByCond(cond);
    }

    private static RowMapper<PoeCurrencyDto> getPoeCurrencyDtoRowMapper() {
        return BeanPropertyRowMapper.newInstance(PoeCurrencyDto.class);
    }
}
