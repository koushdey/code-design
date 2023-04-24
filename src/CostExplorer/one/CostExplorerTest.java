package com.atlassian.CostExplorer;

import static org.junit.Assert.assertEquals;

//import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CostExplorerTest {
    @Test
    public void returnsMonthlyAndYearlyCost()
    {
        Customer customer = new Customer("c1", 
            new Product("JIRA", 
            new Subscription(Plan.BASIC, "2023-04-17")));

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        List<Double> expectation = Arrays.asList(
            0.00,0.00,0.00,4.33, 9.99, 9.99, 9.99, 9.99, 9.99, 9.99, 9.99, 9.99);
        Double yearlyCostExpected = 84.25;
        assertEquals(costExplorer.monthlyCost(), expectation);
        assertEquals(costExplorer.yearlyCost(), yearlyCostExpected);
    }
    
    @Test //LEVEL 1
    public void returnMonthlyAndYearlyCostWithTrialPlan(){
        Customer customer = new Customer("C1", 
            Arrays.asList(new Product("JIRA", 
                Arrays.asList(new Subcription(Plan.BASIC, "2023-05-17"),
                new Subcription(Plan.TRIAL, "2023-04-17")))));

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        List<Double> expectedMonthlyBill = Arrays.asList(
            0.00, 0.00, 0.00, 0.00, 4.33, 9.99, 9.99, 9.99, 9.99, 9.99, 9.99, 9.99
        );
        Double expectedYearlyCost = 9.99*8 + 4.33 - 9.99;
        assertEquals(costExplorer.monthlyCost(), expectedMonthlyBill);
        assertEquals(costExplorer.yearlyCostEstimate(), expectedYearlyCost);
    }

    @Test   //LEVEL 2
    public void returnMonthlyAndYearlyCostWithTrialPlanAndMultipleProducts(){
        Customer customer = new Customer("C1", 
            Arrays.asList(
            new Product("JIRA", 
                Arrays.asList(
                new Subcription(Plan.BASIC, "2023-05-17"),
                new Subcription(Plan.TRIAL, "2023-04-17")
                )
            ),
            new Product("CONFLUENCE",
                Arrays.asList(
                new Subcription(Plan.STANDARD, "2023-03-17"),
                new Subcription(Plan.TRIAL, "2023-01-07")
                )
            )
            ));

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        List<Double> expectedMonthlyBill = Arrays.asList(
            0.00, 0.00, 21.66, 49.99, 54.32, 59.98, 59.98, 59.98, 59.98, 59.98, 59.98, 59.98
        );
        Double expectedYearlyCost = 545.83;

        HashMap<String, Double> expectedYearlyCostPerProduct = new HashMap<>();
        expectedYearlyCostPerProduct.put("JIRA", 74.26);
        expectedYearlyCostPerProduct.put("CONFLUENCE", 471.57);
        assertEquals(costExplorer.monthlyCost(), expectedMonthlyBill);
        assertEquals(costExplorer.yearlyCostEstimate(), expectedYearlyCost);
        assertEquals(costExplorer.yearlyCostPerProduct(), expectedYearlyCostPerProduct);
    }
}

