package com.codecool.plaza.api;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {

    private boolean open;
    private List<Shop> shops = new ArrayList<>();

    public PlazaImpl(boolean open){
        this.open=open;
    }

    public List<Shop> getShops()throws PlazaIsClosedException{
        if(!isOpen()){
            throw new PlazaIsClosedException();
        }
        return shops;
    }

    public void addShop(Shop shop){
        shops.add(shop);
    }

    public void removeShop(Shop shop){
        shops.remove(shop);
    }

    public Shop findShopByName(String name) throws noSuchShopException, PlazaIsClosedException{

        if(!isOpen()){
            throw new PlazaIsClosedException();
        }

        for (Shop shop : shops) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        throw new noSuchShopException();
    }

    public boolean isOpen(){
        return open;
    }

    public void open(){
        open=true;
    }

    public void close(){
        open=false;
    }
}
