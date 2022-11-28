package HwangJiHun.poeitemvalues.model.ninja.dto.database;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Columns Name :
 * LEAGUE_ID,
 * TYPE,
 * ITEM_NAME,
 * PAY_CURRENCY_ID,
 * GET_CURRENCY_ID,
 * TIME,
 * RECEIVE_VALUE,
 * CHAOS_EQUIVALENT,
 * DETAILS_ID
 */
@Getter @Setter
public class UpdateParamDto {

    private int leagueId;
    private String type;
    private String itemName;
    private int payCurrencyId;
    private int getCurrencyId;
    private Timestamp time;
    private double receiveValue;
    private int receiveTotalChange;
    private float chaosEquivalent;
    private String detailsId;
}
