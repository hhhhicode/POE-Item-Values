package HwangJiHun.poeitemvalues.model.ninja;

import lombok.Data;

@Data
public class Currency {

    private String currencyTypeName;
    private MarketPrice pay;
    private MarketPrice receive;
    private SparkLine paySparkLine;
    private SparkLine receiveSparkLine;
    private Float chaosEquivalent;
    private SparkLine lowConfidencePaySparkLine;
    private SparkLine lowConfidenceReceiveSparkLine;
    private String detailsId;
}
