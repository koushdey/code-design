package CostExplorer.two3;

import java.time.LocalDate;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        //LocalDate START_DATE = LocalDate.parse("2023-04-10");
        //boolean flag = LocalDate.now().isAfter(START_DATE.plusDays(30));
        //System.out.println(flag);
        
        Customer customer = new Customer("c1", 
        Arrays.asList( 
            new Product("Jira", 
            Arrays.asList(
                new Subscription(Plan.TRIAL, LocalDate.parse("2023-03-01")),
                new Subscription(Plan.PREMIUM, LocalDate.parse("2023-04-17"))
            )),
            new Product("Confluence", 
            Arrays.asList(
                new Subscription(Plan.TRIAL, LocalDate.parse("2023-02-01")),
                new Subscription(Plan.BASIC, LocalDate.parse("2023-03-21"))
            ))
        ));

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        System.out.println(costExplorer.monthlyCost().toString());
        System.out.println(costExplorer.yearlyCostEstimate().toString());
        System.out.println(costExplorer.yearlyCostPerProduct().toString());
    }
}
