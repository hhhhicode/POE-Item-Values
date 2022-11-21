package HwangJiHun.poeitemvalues.model.ninja;

import lombok.Data;

import java.util.List;

@Data
public class DivinationCard {

    private Integer id;
    private String name;
    private String icon;
    private Integer stackSize;
    private String artFilename;
    private Integer itemClass;
    private SparkLine sparkline;
    private SparkLine lowConfidenceSparkline;
    private List<CardModifiers> implicitModifiers;
    private List<CardModifiers> explicitModifiers;
    private String flavourText;
    private Double chaosValue;
    private Double exaltedValue;
    private Double divineValue;
    private Integer count;
    private String detailsId;
    private Integer listingCount;
}
