package service;

import models.Sweets;
import service.interfaces.SweetBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SweetBoxImpl implements SweetBox {

    private List<Sweets> sweetList;

    public SweetBoxImpl(List<Sweets> sweetList) {
        this.sweetList = new ArrayList<Sweets>(sweetList);
    }

    @Override
    public void addSweet(Sweets sweet) {
        sweetList.add(sweet);
    }

    @Override
    public void removeSweet() {
        sweetList.remove(sweetList.size() - 1);
    }

    @Override
    public void removeSweet(int index) {
        sweetList.remove(index);
    }

    @Override
    public void showWeight() {
        System.out.println("Общий вес коробки со сладостями составляет: " + String.format("%.3f", searchWeight()) + " кг.");
    }

    @Override
    public void showPriceOfSweetBox() {
        System.out.println("Стоимость коробки со сладостями составляет: " + String.format("%.2f", searchPriceOfSweetBox()) + " руб.");
    }

    @Override
    public void showInfoOfSweets() {
        for (Sweets sweets : sweetList) {
            System.out.println(sweets);
        }
    }

    @Override
    public void smartGiftOptimizationByWeight(double requiredWeight) {

        if (requiredWeight <= 0.0) {
            sweetList.clear();
            return;
        }

        double weightOfBox = searchWeight();

        Collections.sort(this.sweetList, new Comparator<Sweets>() {
            @Override
            public int compare(Sweets o1, Sweets o2) {
                if (((o1.getWeight() - o2.getWeight()) < 0)) {
                    return -1;
                }
                if (((o1.getWeight() - o2.getWeight()) > 0)) {
                    return -1;
                }
                return 0;
            }
        });
        while (true) {
            if (weightOfBox <= requiredWeight) {
                break;
            } else {
                weightOfBox -= sweetList.get(0).getWeight();
                sweetList.remove(0);
            }
        }
    }

    @Override
    public void smartGiftOptimizationByPrice(double requiredPrice) {

        if (requiredPrice <= 0.0) {
            sweetList.clear();
            return;
        }

        double totalPrice = searchPriceOfSweetBox();

        Collections.sort(this.sweetList, new Comparator<Sweets>() {
            @Override
            public int compare(Sweets o1, Sweets o2) {
                if (((o1.getPrice() - o2.getPrice()) < 0)) {
                    return -1;
                }
                if (((o1.getPrice() - o2.getPrice()) > 0)) {
                    return -1;
                }
                return 0;
            }
        });
        while (true) {
            if (totalPrice <= requiredPrice) {
                break;
            } else {
                totalPrice -= sweetList.get(0).getPrice();
                sweetList.remove(0);
            }
        }
    }

    private double searchWeight() {
        if (sweetList.size() < 1) {
            return 0;
        }

        double totalWeight = 0;
        for (Sweets sweets : sweetList) {
            totalWeight += sweets.getWeight();
        }
        return totalWeight;
    }

    private double searchPriceOfSweetBox() {
        if (sweetList.size() < 1) {
            return 0;
        }

        double totalPrice = 0;
        for (Sweets sweets : sweetList) {
            totalPrice += sweets.getPrice();
        }
        return totalPrice;
    }
}
