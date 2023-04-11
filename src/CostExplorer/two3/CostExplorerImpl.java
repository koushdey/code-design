package CostExplorer.two3;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CostExplorerImpl implements CostExplorer {

    //private Customer customer;
    //private Plan plan;
    //private LocalDate startDate;
    //private List<Subscription> subscriptions;
    private List<Product> products;
    private Map<String, Double[]> pricingReportMap;

    CostExplorerImpl(Customer customer){
        //this.customer = customer;
        //this.plan = customer.product().subscription().plan();
        //this.startDate = customer.product().subscription().starDate();
        //this.subscriptions = customer.product().subscriptions();
        this.products = customer.products();

        pricingReportMap = new HashMap<>();
        for(Product product : products){
            Double[] prices = new Double[12];
            Arrays.fill(prices, 0.00);
            pricingReportMap.put(product.Name(), prices);
        }
        processBilling();
    }

    @Override
    public List<Double> monthlyCost() {
        //pricingReportMap.values().stream().map(d -> Arrays.stream(d).mapToDouble(Double::doubleValue).sum());
        Double[] monthlyBill = new Double[12];
        Arrays.fill(monthlyBill, 0.00);

        int index = 0;
        while(index < 12){
            for(Double[] price : pricingReportMap.values()){
                monthlyBill[index] += price[index];
            }
            index++;
        }
        
        return Arrays.asList(monthlyBill);
    }

    @Override
    public Map<String, Double> yearlyCostPerProduct() {
        Map<String, Double> result= pricingReportMap.entrySet().stream()
                                    .collect(Collectors.toMap(e -> e.getKey(), e -> Arrays.asList(e.getValue()).stream().mapToDouble(Double::doubleValue).sum()));
        //System.out.println(result);
        return result;
    }

    @Override
    public Double yearlyCostEstimate() {
        
        return pricingReportMap.values().stream()
        .mapToDouble(
            d -> Arrays.stream(d).mapToDouble(Double::doubleValue).sum())
            .sum();
    }

    private void processBilling(){
        for(Product product : products){
            Subscription billedSubscription;

            Optional<Subscription> trialSub = product.subscriptions().stream().filter(o -> o.plan().equals(Plan.TRIAL)).findAny();
            if(!trialSub.isPresent()){
                billedSubscription = product.subscriptions().get(0);  
            } else{
                billedSubscription = product.subscriptions().get(1);
            }

            Plan billedPlan = billedSubscription.plan();
            int month = billedSubscription.starDate().getMonthValue();
            int day = billedSubscription.starDate().getDayOfMonth();

            DecimalFormat df = new DecimalFormat("#.##");
            Double[] prices = pricingReportMap.get(product.Name());
            prices[month-1]  = Double.valueOf(df.format(billedPlan.getPrice() - billedPlan.getPrice()/30*day));
            for(int i=month; i < 12; i++){
                prices[i] = billedPlan.getPrice();
            }
            pricingReportMap.put(product.Name(), prices);
        }
    }
    
}
