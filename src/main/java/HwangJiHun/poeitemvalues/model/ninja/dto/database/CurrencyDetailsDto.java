package HwangJiHun.poeitemvalues.model.ninja.dto.database;

import lombok.*;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CurrencyDetailsDto {
    private final int id;
    private String icon;
    private String name;
    private String tradeId;
}
