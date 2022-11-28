package HwangJiHun.poeitemvalues.model.ninja.dto.database;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PoeCurrencyDto {

    private Long id;
    private String currencyTypeName;
    private Integer payId;
    private Integer payLeagueId;
    private Integer payPayCurrencyId;
    private Integer payGetCurrencyId;
    private Timestamp paySampleTimeUtc;
    private Integer payCount;
    private Double payValue;
    private Integer payDataPointCount;
    private Boolean payIncludesSecondary;
    private Integer payListingCount;
    private Integer receiveId;
    private Integer receiveLeagueId;
    private Integer receivePayCurrencyId;
    private Integer receiveGetCurrencyId;
    private Timestamp receiveSampleTimeUtc;
    private Integer receiveCount;
    private Double receiveValue;
    private Integer receiveDataPointCount;
    private Boolean receiveIncludesSecondary;
    private Integer receiveListingCount;
    private Double paySparkLineData0;
    private Double paySparkLineData1;
    private Double paySparkLineData2;
    private Double paySparkLineData3;
    private Double paySparkLineData4;
    private Double paySparkLineData5;
    private Double paySparkLineData6;
    private Double paySparkLineTotalChange;
    private Double receiveSparkLineData0;
    private Double receiveSparkLineData1;
    private Double receiveSparkLineData2;
    private Double receiveSparkLineData3;
    private Double receiveSparkLineData4;
    private Double receiveSparkLineData5;
    private Double receiveSparkLineData6;
    private Double receiveSparkLineTotalChange;
    private Float chaosEquivalent;
    private String detailsId;
    private String itemType;
}
