package CostExplorer.one;

import java.util.List;

public interface CostExplorer {
    List<Double> monthlyCost();
    Double yearlyCost();
    //Map<String, Double> yearlyCostPerProduct();   //LEVEL 2
    //Double yearlyCostEstimate();  //LEVEL 2
}
