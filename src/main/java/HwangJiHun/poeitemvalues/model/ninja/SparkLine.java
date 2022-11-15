package HwangJiHun.poeitemvalues.model.ninja;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SparkLine {

    private List<Double> data = new ArrayList<>(7);
    private Double totalChange;
}
