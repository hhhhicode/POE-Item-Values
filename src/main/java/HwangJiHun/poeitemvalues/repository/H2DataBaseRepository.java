package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.UpdateParamDto;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

    public H2DataBaseRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("poeitemvalues");
    }

    public void save(UpdateParamDto updateParamDto) {
//        SqlParameterSource param = new MapSqlParameterSource()
//                .addValue("LEAGUE_ID", currency.getReceive().getLeagueId())
//                .addValue("TYPE", ApiType.CURRENCY.getTypeName())
//                .addValue("ITEM_NAME", currency.getCurrencyTypeName())
//                .addValue("PAY_CURRENCY_ID", currency.getReceive().getPayCurrencyId())
//                .addValue("GET_CURRENCY_ID", currency.getReceive().getGetCurrencyId())
//                .addValue("TIME", currency.getReceive().getSampleTimeUtc())
//                .addValue("RECEIVE_VALUE", currency.getReceive().getValue())
//                .addValue("CHAOS_EQUIVALENT", currency.getChaosEquivalent())
//                .addValue("DETAILS_ID", currency.getDetailsId());
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
                    return updateParamDto;
                });
    }

    public UpdateParamDto transferCurrencyToUpdateParamDto(Currency currency) {
        UpdateParamDto updateParamDto = new UpdateParamDto();
        updateParamDto.setLeagueId(currency.getReceive().getLeagueId());
        updateParamDto.setType(ItemType.CURRENCY.getTypeName());
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
