package CostExplorer.two3;

public enum Plan {

    TRIAL(0.00),
    BASIC(9.99),
    STANDARD(49.99),
    PREMIUM(249.99);
    
    private Double price;
    Plan(double price){
        this.price = price;
    }

    public Double getPrice(){
        return this.price;
    }
}
