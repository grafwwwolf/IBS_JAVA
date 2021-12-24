package service.interfaces;

import models.Sweets;

public interface SweetBox {

    void addSweet(Sweets sweet);
    void removeSweet();
    void removeSweet(int index);
    void showWeight();
    void showPriceOfSweetBox();
    void showInfoOfSweets();

    // методы для умной оптимизации:
    void smartGiftOptimizationByWeight(double requiredWeight);
    void smartGiftOptimizationByPrice(double requiredPrice);
}
