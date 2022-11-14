package HwangJiHun.poeitemvalues.model.ninja;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

//TODO 사용처 주석 달기

/**
 * id : ?
 * league_id : 리그의 id
 * pay_currency_id : 주는 currency id
 * get_currency_id : 받는 currency id
 * sample_time_utc : 데이터 시간
 *
 */
@Data
public class MarketPrice {

    private Integer id;
    @JsonProperty(value = "league_id")
    private Integer leagueId;
    @JsonProperty(value = "pay_currency_id")
    private Integer payCurrencyId;
    @JsonProperty(value = "get_currency_id")
    private Integer getCurrencyId;
    @JsonProperty(value = "sample_time_utc")
    private Date sampleTimeUtc;
    private Integer count;
    private Double value;
    @JsonProperty(value = "data_point_count")
    private Integer dataPointCount;
    @JsonProperty(value = "includes_secondary")
    private Boolean includesSecondary;
    @JsonProperty(value = "listing_count")
    private Integer listingCount;
}
