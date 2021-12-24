package models;

public class Candies extends Sweets {

    private boolean softness;

    public Candies(String title, double weight, double price) {
        super(title, weight, price);
        this.softness = true;
    }

    public Candies(String title, double weight, double price, boolean softness) {
        super(title, weight, price);
        this.softness = softness;
    }

    @Override
    public String toString() {
        return "Конфеты: " + title + ", вес: " + weight + " кг, цена: " + price + (softness ? " руб., мягкие." : " руб., карамельные.");
    }
}
