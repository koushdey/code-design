package CostExplorer.one;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
//import java.util.stream.*;  //LEVEL 2

public class CostExplorerImpl implements CostExplorer {

    private Plan plan;
    private LocalDate startDate;
    //private List<Subscription> subscriptions;     //LEVEL 1 comment startDate & plan
    
    //private List<Product> products;	//LEVEL 2 comment above lines
    //private Map<String, Double[]> pricingReportMap;
    
    private Double[] prices;
    
    private DecimalFormat df = new DecimalFormat("#.##");

    CostExplorerImpl(Customer customer){
        this.plan = customer.product().subscription().plan();
        this.startDate = customer.product().subscription().starDate();
        //this.subscriptions = customer.product().subscriptions();    //LEVEL 1 comment startDate & plan
        prices = new Double[12];
        Arrays.fill(prices, 0.00);
        
        /* LEVEL 2 Comment all lines above
        this.products = customer.products();	
        pricingReportMap = new HashMap<>();	
        for(Product product : products){	
            Double[] prices = new Double[12];	
            Arrays.fill(prices, 0.00);	
            pricingReportMap.put(product.Name(), prices);	
        }	
        processBilling();
        */
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
        
        /* LEVEL 1 comment above lines
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
        */
        //-------------------------------//
        /*  LEVEL 2 comment all lines in the method
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
        */
    }

    @Override
    public Double yearlyCost() {
        double finalPrice = 0.00;
        for(double price : prices){
            finalPrice += price;
        }
        return finalPrice;
    }
    
    // LEVEL 2 All methods below
    /*
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
    */
    
}
