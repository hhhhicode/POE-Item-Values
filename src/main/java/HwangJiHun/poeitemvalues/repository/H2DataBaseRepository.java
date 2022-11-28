package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.UpdateParamDto;
import HwangJiHun.poeitemvalues.repository.mybatis.ItemSearchCond;
import HwangJiHun.poeitemvalues.repository.mybatis.UpdateParamDtoMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class H2DataBaseRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final UpdateParamDtoMapper updateParamDtoMapper;

    public H2DataBaseRepository(DataSource dataSource, UpdateParamDtoMapper updateParamDtoMapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("poeitemvalues");
        this.updateParamDtoMapper = updateParamDtoMapper;
    }

    public void save(UpdateParamDto updateParamDto) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(updateParamDto);

        jdbcInsert.execute(param);
    }

    public List<UpdateParamDto> findAll() {
        String sql = "select * from POEITEMVALUES";

        return jdbcTemplate.query(sql,
                (rs, rn) -> {
                    UpdateParamDto updateParamDto = new UpdateParamDto();
                    updateParamDto.setType(rs.getString("type"));
                    updateParamDto.setTime(rs.getTimestamp("time"));
                    updateParamDto.setItemName(rs.getString("item_name"));
                    updateParamDto.setReceiveValue(rs.getDouble("receive_value"));
                    updateParamDto.setChaosEquivalent(rs.getFloat("chaos_equivalent"));
                    updateParamDto.setDetailsId(rs.getString("details_id"));
                    updateParamDto.setGetCurrencyId(rs.getInt("get_currency_id"));
                    updateParamDto.setPayCurrencyId(rs.getInt("pay_currency_id"));
                    updateParamDto.setLeagueId(rs.getInt("league_id"));
                    updateParamDto.setReceiveTotalChange(rs.getInt("RECEIVE_TOTAL_CHANGE"));
                    return updateParamDto;
                });
    }

    public List<UpdateParamDto> findByItemType(String itemType) {
        String sql = "SELECT * FROM POEITEMVALUES WHERE type = :type";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("type", itemType);

        return jdbcTemplate.query(sql, param, getUpdateParamDtoBeanPropertyRowMapper());
    }

    public List<UpdateParamDto> findByCond(ItemSearchCond cond) {
        return updateParamDtoMapper.findByCond(cond);
    }

    private static BeanPropertyRowMapper<UpdateParamDto> getUpdateParamDtoBeanPropertyRowMapper() {
        return new BeanPropertyRowMapper<>(UpdateParamDto.class);
    }

    public UpdateParamDto transferCurrencyToUpdateParamDto(Currency currency, String currencyType) {
        UpdateParamDto updateParamDto = new UpdateParamDto();
        updateParamDto.setLeagueId(currency.getReceive().getLeagueId());
        updateParamDto.setType(currencyType);
        updateParamDto.setItemName(currency.getCurrencyTypeName());
        updateParamDto.setPayCurrencyId(currency.getReceive().getPayCurrencyId());
        updateParamDto.setGetCurrencyId(currency.getReceive().getGetCurrencyId());
        updateParamDto.setTime(currency.getReceive().getSampleTimeUtc());
        updateParamDto.setReceiveValue(currency.getChaosEquivalent());
        updateParamDto.setChaosEquivalent(currency.getChaosEquivalent());
        updateParamDto.setDetailsId(currency.getDetailsId());

        return updateParamDto;
    }
}
