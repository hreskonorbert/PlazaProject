package com.codecool.plaza.api;

import java.util.List;

public interface Shop {

    String getName();
    String getOwner();
    boolean isOpen();
    void open();
    void close();
    List<Product> findByName(String name) throws ShopIsClosedException;
    boolean hasProduct(Product product, int quantity, float price) throws ShopIsClosedException;
    void addNewProduct(Product product, int quantity,float price) throws NoSuchProductException, ShopIsClosedException;
    void addProduct(long barcode, int quantity);
    List<Product> buyProduct(long barcode, int quantity) throws NoSuchProductException, ShopIsClosedException;
    String toString();
    }
