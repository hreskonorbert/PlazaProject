package com.codecool.plaza.api;

import java.util.Date;

public class FoodProduct extends Product{
    private int calories;
    private Date bestBefore;

    public FoodProduct(long barcode, String manufacturer,String name,int calories, Date bestBefore,float price){
        super(barcode,manufacturer,name,price);
        this.calories=calories;
        this.bestBefore=bestBefore;
    }
     public boolean isStillConsumable(){
        Date presentTime = new Date();
        if(presentTime.getTime()<bestBefore.getTime()){
            return true;
        }else{
            return false;
        }
     }

     public int getCalories(){
        return calories;
     }

     public String toString(){
        return Integer.toString(calories);
     }



}
