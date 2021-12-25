package models;

public class Cookies extends Sweets {

    private boolean friability;

    public Cookies(String title, double weight, double price) {
        super(title, weight, price);
        this.friability = true;
    }

    public Cookies(String title, double weight, double price, boolean softness) {
        super(title, weight, price);
        this.friability = softness;
    }

    @Override
    public String toString() {
        return "Печенье: " + title + ", вес: " + weight + " кг, цена: " + price + (friability ? " руб., рассыпчатые." : " руб., несыпучие.");
    }
}
