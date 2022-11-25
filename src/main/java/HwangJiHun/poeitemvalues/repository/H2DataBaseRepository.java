package HwangJiHun.poeitemvalues.repository;

import HwangJiHun.poeitemvalues.model.ninja.Currency;
import HwangJiHun.poeitemvalues.model.ninja.dto.database.UpdateParamDto;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
