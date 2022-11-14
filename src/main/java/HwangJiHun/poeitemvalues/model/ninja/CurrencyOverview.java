package HwangJiHun.poeitemvalues.model.ninja;

import lombok.Data;

import java.util.List;

/**
 * lines[]
 * ---- currencyTypeName
 * ---- pay{}
 * -------- id
 * -------- league_id
 * -------- pay_currency_id
 * -------- get_currency_id
 * -------- sample_time_utc
 * -------- count
 * -------- value
 * -------- data_point_count
 * -------- includes_secondary
 * -------- listing_count
 * ---- receive{}
 * -------- id
 *-------- league_id
 * -------- pay_currency_id
 * -------- get_currency_id
 * -------- sample_time_utc
 * -------- count
 * -------- value
 * -------- data_point_count
 * -------- includes_secondary
 * -------- listing_count
 * ---- paySparkLine{}
 * -------- data[]
 * -------- totalChange
 * ---- receiveSparkLine
 * -------- data[]
 * -------- totalChange
 * ---- chaosEquivalent
 * ---- lowConfidencePaySparkLine{}
 * ---- lowConfidenceReceiveSparkLine{}
 * ---- detailsId
 * currencyDetails[]
 * language[]
 * ---- name
 * ---- translations
 */
@Data
public class CurrencyOverview {

    private List<Currency> lines;
    private List<CurrencyDetail> currencyDetails;
    private Language language;
}
