package CostExplorer.one;

public enum Plan {

    BASIC(9.99),
    STANDARD(49.99),
    PREMIUM(249.99);
    
    private Double price;
    Plan(Double price){
        this.price = price;
    }

    public Double getPrice(){
        return this.price;
    }
}
