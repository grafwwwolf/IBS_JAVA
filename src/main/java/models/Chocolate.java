package models;

public class Chocolate extends Sweets {

    private boolean milkiness;

    public Chocolate(String title, double weight, double price) {
        super(title, weight, price);
        this.milkiness = true;
    }

    public Chocolate(String title, double weight, double price, boolean softness) {
        super(title, weight, price);
        this.milkiness = softness;
    }

    @Override
    public String toString() {
        return "Шоколад: " + title + ", вес: " + weight + " кг, цена: " + price + (milkiness ? " руб., молочный." : " руб., темный.");
    }
}
