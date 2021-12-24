package models;

public class ChocolateBunny extends Sweets {

    private int heightInCentimeters;

    public ChocolateBunny(String title, double weight, double price) {
        super(title, weight, price);
        this.heightInCentimeters = 10;
    }

    public ChocolateBunny(String title, double weight, double price, int heightInCentimeters) {
        super(title, weight, price);
        this.heightInCentimeters = heightInCentimeters;
    }

    @Override
    public String toString() {
        return "Шоколадный заяц: " + title + ", вес: " + weight + " кг, цена: " + price + " руб., высота: " + heightInCentimeters + " сантиметров.";
    }
}
