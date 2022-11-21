package HwangJiHun.poeitemvalues.model.ninja.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CardsDataListDto {

    private List<TotalChange5Currency> currencyTop5Cards;
    private List<TotalChange5Currency> currencyBottom5Cards;
}
