package HwangJiHun.poeitemvalues.model.ninja;

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
    private Integer league_id;
    private Integer pay_currency_id;
    private Integer get_currency_id;
    private Date sample_time_utc;
    private Integer count;
    private Double value;
    private Integer data_point_count;
    private Boolean includes_secondary;
    private Integer listing_count;
}
