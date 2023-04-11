package CostExplorer.one;

import java.time.LocalDate;

record Subscription(Plan plan, LocalDate starDate){};
record Product(String Name, Subscription subscription){};
record Customer(String Name, Product product){};
