package models;

public abstract class Sweets {

    protected String title;
    protected double weight;
    protected double price;

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public Sweets(String title, double weight, double price) {
        this.title = title;
        this.weight = weight;
        this.price = price;
    }
}
