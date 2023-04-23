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

    @Test     //LEVEL 1
    /*public void returnsMonthlyAndYearlyCostWithTrial()
    {
        Customer customer = new Customer("c1", 
            new Product("JIRA", 
            Arrays.asList(new Subscription(Plan.BASIC, "2023-05-17"),
            new Subscription(Plan.TRIAL, "2023-04-17"))));

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        List<Double> expectation = Arrays.asList(
            0.00,0.00,0.00,0.00, 4.33, 9.99, 9.99, 9.99, 9.99, 9.99, 9.99, 9.99);
        Double yearlyCostExpected = 84.25 - 9.99;
        assertEquals(costExplorer.monthlyCost(), expectation);
        assertEquals(costExplorer.yearlyCost(), yearlyCostExpected);
    }

    @Test
    /*public void returnsMonthlyAndYearlyCostWithInvalidDate()
    {
        Customer customer = new Customer("c1", 
            new Product("JIRA", 
            Arrays.asList(new Subscription(Plan.BASIC, "2023-23-17"),
            new Subscription(Plan.TRIAL, "2023-23-17"))));

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        List<Double> expectation = Arrays.asList(
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Double yearlyCostExpected = 0.0;
        assertEquals(costExplorer.monthlyCost(), expectation);
        assertEquals(costExplorer.yearlyCost(), yearlyCostExpected);
    }*/
}

