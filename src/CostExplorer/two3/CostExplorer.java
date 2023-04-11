package CostExplorer.two3;

import java.util.List;
import java.util.Map;

public interface CostExplorer {
    List<Double> monthlyCost();
    Map<String, Double> yearlyCostPerProduct();
    Double yearlyCostEstimate();
}
