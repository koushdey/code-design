package CostExplorer.one;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CostExplorerImpl implements CostExplorer {

    //private Customer customer;
    private Plan plan;
    private LocalDate startDate;
    private Double[] prices;
    private DecimalFormat df = new DecimalFormat("#.##");

    CostExplorerImpl(Customer customer){
        //this.customer = customer;
        this.plan = customer.product().subscription().plan();
        this.startDate = customer.product().subscription().starDate();
        prices = new Double[12];
        Arrays.fill(prices, 0.00);
    }

    @Override
    public List<Double> monthlyCost() {
        int month = startDate.getMonthValue();
        int day = startDate.getDayOfMonth();

        prices[month-1]  = Double.valueOf(df.format(plan.getPrice() - plan.getPrice()/30*day));
        for(int i=month; i < 12; i++){
            prices[i] = plan.getPrice();
        }
        return Arrays.asList(prices);
    }

    @Override
    public Double yearlyCost() {
        double finalPrice = 0.00;
        for(double price : prices){
            finalPrice += price;
        }
        return finalPrice;
    }
    
}
