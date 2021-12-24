import models.*;
import service.SweetBoxImpl;
import service.interfaces.SweetBox;

import java.util.ArrayList;
import java.util.List;

public class Api {

    public static void main(String[] args) {

        List<Sweets> sweetList = new ArrayList<Sweets>();

        sweetList.add(new Candies("Ласточка", 0.35, 85.50));
        sweetList.add(new Candies("Карамелька", 0.3, 45.50, false));
        sweetList.add(new Cookies("Сдобное", 0.2, 30.50, false));
        sweetList.add(new Cookies("Мякиш", 0.15, 25.50));
        sweetList.add(new Waffles("Лакомка", 0.25, 28.50));
        sweetList.add(new Waffles("Ванильные", 0.25, 30.20, false));
        sweetList.add(new Chocolate("Воздушный", 0.085, 81.50));
        sweetList.add(new Chocolate("Воздушный Темный", 0.085, 81.50, false));
        sweetList.add(new ChocolateBunny("Шоколадный заяц", 0.12, 85.80));

        SweetBox sweetBox = new SweetBoxImpl(sweetList);

        sweetBox.showWeight();
        sweetBox.showPriceOfSweetBox();
        sweetBox.showInfoOfSweets();

        System.out.println("----------------------------------------------");

        sweetBox.removeSweet();
        sweetBox.removeSweet(1);
        sweetBox.addSweet(new ChocolateBunny("Супер Шоколадный заяц", 0.2, 105.50, 15));

        System.out.println("----------------------------------------------");

        sweetBox.showWeight();
        sweetBox.showPriceOfSweetBox();
        sweetBox.showInfoOfSweets();

        System.out.println("----------------------------------------------");

        sweetBox.smartGiftOptimizationByWeight(3);
        sweetBox.smartGiftOptimizationByPrice(106);

        System.out.println("----------------------------------------------");

        sweetBox.showWeight();
        sweetBox.showPriceOfSweetBox();
        sweetBox.showInfoOfSweets();
    }
}
