package CostExplorer.two;

import java.time.LocalDate;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        //LocalDate START_DATE = LocalDate.parse("2023-04-10");
        //boolean flag = LocalDate.now().isAfter(START_DATE.plusDays(30));
        //System.out.println(flag);
        
        Customer customer = new Customer("c1", 
        new Product("Jira", 
        Arrays.asList(
            new Subscription(Plan.TRIAL, LocalDate.parse("2023-03-01")),
            new Subscription(Plan.PREMIUM, LocalDate.parse("2023-04-17"))
        )));

        

        CostExplorerImpl costExplorer = new CostExplorerImpl(customer);
        System.out.println(costExplorer.monthlyCost().toString());
        System.out.println(costExplorer.yearlyCost().toString());
    }
}
