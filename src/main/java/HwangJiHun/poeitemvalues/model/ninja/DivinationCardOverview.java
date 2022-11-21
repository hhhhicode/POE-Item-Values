package HwangJiHun.poeitemvalues.model.ninja;

import lombok.Data;

import java.util.List;

@Data
public class DivinationCardOverview {

    private List<DivinationCard> lines;
    private Language language;
}
