package com.atlassian.CostExplorer;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//import java.util.Optional;        //LEVEL 1
//import java.util.Map;   //LEVEL 2
//import java.util.stream.*;  //LEVEL 2


public class CostExplorerImpl implements CostExplorer{
    private Product product;
    private HashMap<String, Double[]> pricingReportMap;
    //private List<Product> products;     //LEVEL 2 
    private DecimalFormat df = new DecimalFormat("#.##");

    
    CostExplorerImpl(Customer customer){
        pricingReportMap = new HashMap<>();

        if(!customer.name().isEmpty() && customer.product()!= null) {
            this.product = customer.product();

            Double[] expense = new Double[12];
            Arrays.fill(expense, 0.00);
            pricingReportMap.put(product.name(), expense);

            processBilling();
        }    
    }

    @Override
    public List<Double> monthlyCost() {
        /*-------- LEVEL 2 ----------
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
        -------- LEVEL 2 ----------*/
        return Arrays.asList(pricingReportMap.get(product.name()));
    }

    @Override
    public Double yearlyCost() {
        Double totalCost = 0.00;
        for(Double cost : pricingReportMap.get(product.name())){
            totalCost += cost;
        } 
        return totalCost;
    }

    /*-------- LEVEL 2 ----------
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
    -------- LEVEL 2 ----------*/

    private void processBilling(){
        //for(Product product : products){  //LEVEL 2
            if(!product.name().isEmpty() && product.subscription() != null ){
                //&& !product.subscriptions().isEmpty()){     //LEVEL 1
                 Subscription billedSubscription = product.subscription();
                //Optional<Subscription> billedSubscription = product.subscriptions().stream()    //LEVEL 1
                //    .filter(o -> !o.plan().equals(Plan.TRIAL)).findAny(); //LEVEL 1
                
                //if(billedSubscription.isPresent()){ //LEVEL 1
                    Plan billedPlan = billedSubscription.plan();
                    int month = LocalDate.parse(billedSubscription.startDate()).getMonthValue();
                    int day = LocalDate.parse(billedSubscription.startDate()).getDayOfMonth();

                    //Plan billedPlan = billedSubscription.get().plan();        //LEVEL 1
                    //int month = LocalDate.parse(billedSubscription.get().startDate()).getMonthValue();    //LEVEL 1
                    //int day = LocalDate.parse(billedSubscription.get().startDate()).getDayOfMonth();      //LEVEL 1

                    Double[] prices = pricingReportMap.get(product.name());
                    prices[month-1]  = Double.valueOf(df.format(billedPlan.getPrice() - billedPlan.getPrice()/30*day));
                    for(int i=month; i < 12; i++){
                        prices[i] = billedPlan.getPrice();
                    }
                    pricingReportMap.put(product.name(), prices);
                //}       //LEVEL 1
                
            }
        //}     LEVEL 2
    }
}
