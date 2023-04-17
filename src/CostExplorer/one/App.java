package CostExplorer.one;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        //LocalDate START_DATE = LocalDate.parse("2023-04-10");
        //boolean flag = LocalDate.now().isAfter(START_DATE.plusDays(30));
        //System.out.println(flag);
        
        Customer customer = new Customer("c1", 
        new Product("Jira", 
        new Subscription(Plan.PREMIUM, LocalDate.parse("2023-04-17"))));
        /*LEVEL 1 Comment above line
        Arrays.asList(
            new Subscription(Plan.TRIAL, LocalDate.parse("2023-03-01")),
            new Subscription(Plan.PREMIUM, LocalDate.parse("2023-04-17"))
        )));
        */

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        System.out.println(costExplorer.monthlyCost().toString());
        System.out.println(costExplorer.yearlyCost().toString());
        
        //LEVEL 2 Comment above lines
        /*
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
        */
        
    }
}
