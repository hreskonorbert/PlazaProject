package com.codecool.plaza.api;

public abstract class Product {
    protected long barcode;
    protected String manufacturer;
    protected String name;

    public float getPrice() {
        return price;
    }

    protected float price;
    protected Product(long barcode, String manufacturer, String name, float price){
        this.barcode=barcode;
        this.manufacturer=manufacturer;
        this.name=name;
        this.price=price;

    }

    public long getBarcode(){
        return barcode;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public String toString(){
        return Long.toString(barcode);
    }

    public String getName(){
        return name;
    }
}
