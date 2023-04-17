package CostExplorer.one;

import java.time.LocalDate;

record Subscription(Plan plan, LocalDate starDate){};
record Product(String Name, Subscription subscription){};
//record Product(String Name, List<Subscription> subscriptions){};  //LEVEL 1
record Customer(String Name, Product product){};
//record Customer(String Name, List<Product> products){};   //LEVEL 2
