package HwangJiHun.poeitemvalues.repository.mybatis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString
public class ItemSearchCond {

    private String itemName;
    private Double bottomPrice;
    private Double topPrice;
    private Integer percentage;
}
