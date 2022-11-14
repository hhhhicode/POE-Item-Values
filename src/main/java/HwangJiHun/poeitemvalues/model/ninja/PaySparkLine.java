package HwangJiHun.poeitemvalues.model.ninja;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaySparkLine {

    private List<Float> data = new ArrayList<>(7);
    private Float totalChange;
}
