package com.codecool.plaza.api;

import java.util.List;

public interface Plaza {
    List<Shop> getShops() throws PlazaIsClosedException;
    void addShop(Shop shop) throws ShopAlreadyExistsException, PlazaIsClosedException;
    void removeShop(Shop shop) throws noSuchShopException, PlazaIsClosedException;
    Shop findShopByName(String name) throws noSuchShopException, PlazaIsClosedException;
    boolean isOpen();
    void open();
    void close();
    String toString();
}
