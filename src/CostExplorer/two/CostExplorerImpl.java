package CostExplorer.two;

import java.text.DecimalFormat;
import java.util.*;
import java.util.Optional;

public class CostExplorerImpl implements CostExplorer {

    //private Customer customer;
    //private Plan plan;
    //private LocalDate startDate;
    private List<Subscription> subscriptions;
    private Double[] prices;

    CostExplorerImpl(Customer customer){
        //this.customer = customer;
        //this.plan = customer.product().subscription().plan();
        //this.startDate = customer.product().subscription().starDate();
        this.subscriptions = customer.product().subscriptions();
        prices = new Double[12];
        Arrays.fill(prices, 0.00);
    }

    @Override
    public List<Double> monthlyCost() {
        Subscription billedSubscription;

        Optional<Subscription> trialSub = subscriptions.stream().filter(o -> o.plan().equals(Plan.TRIAL)).findAny();
        if(!trialSub.isPresent()){
            billedSubscription = subscriptions.get(0);  
        } else{
            billedSubscription = subscriptions.get(1);
        }

        Plan billedPlan = billedSubscription.plan();
        int month = billedSubscription.starDate().getMonthValue();
        int day = billedSubscription.starDate().getDayOfMonth();

        DecimalFormat df = new DecimalFormat("#.##");
        prices[month-1]  = Double.valueOf(df.format(billedPlan.getPrice() - billedPlan.getPrice()/30*day));
        for(int i=month; i < 12; i++){
            prices[i] = billedPlan.getPrice();
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
