package com.codecool.plaza.api;

public class ClothingProduct extends Product{


    private String material;
    private String type;

    public ClothingProduct(long barcode, String manufacturer,String name, String material, String type,float price){
         super(barcode,manufacturer,name,price);
         this.material=material;
         this.type=type;
     }

    public String getMaterial() {
        return material;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return null;
    }





}
