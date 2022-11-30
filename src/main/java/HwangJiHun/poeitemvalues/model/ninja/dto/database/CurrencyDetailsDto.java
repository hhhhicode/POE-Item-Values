package HwangJiHun.poeitemvalues.model.ninja.dto.database;

import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor
public class CurrencyDetailsDto {
    private Integer id;
    private String icon;
    private String name;
    private String tradeId;

    public CurrencyDetailsDto(){};
}
