package models;

public class Waffles extends Sweets {

    private boolean crispness;

    public Waffles(String title, double weight, double price) {
        super(title, weight, price);
        this.crispness = true;
    }

    public Waffles(String title, double weight, double price, boolean softness) {
        super(title, weight, price);
        this.crispness = softness;
    }

    @Override
    public String toString() {
        return "Вафли: " + title + ", вес: " + weight + " кг, цена: " + price + (crispness ? " руб., хрустящие." : " руб., мягкие.");
    }
}
