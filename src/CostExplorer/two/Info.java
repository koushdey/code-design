package CostExplorer.two;

import java.util.List;
import java.time.LocalDate;

record Subscription(Plan plan, LocalDate starDate){};
record Product(String Name, List<Subscription> subscriptions){};
record Customer(String Name, Product product){};
