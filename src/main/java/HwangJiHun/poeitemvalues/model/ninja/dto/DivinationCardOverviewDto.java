package HwangJiHun.poeitemvalues.model.ninja.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DivinationCardOverviewDto {

    private final String name;
    private final String icon;
    private final Integer stackSize;
    private final Double value;
    private final List<Double> chartData;
    private final String totalChange;
    private final Integer listingCount;
}
