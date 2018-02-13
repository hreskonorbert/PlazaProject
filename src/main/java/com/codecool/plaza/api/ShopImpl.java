package com.codecool.plaza.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop{
    private String name;
    private String owner;
    private Map<Long,ShopEntry> products = new HashMap<>();
    private boolean open=false;

    public ShopImpl(String name, String owner){
        this.name=name;
        this.owner=owner;
    }

    public String getOwner() {
        return owner;
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

    public Map<Product,Integer> getAvaliableProducts(){
        Map<Product,Integer> productList = new HashMap<>();
        for(ShopEntry sE : products.values()){
            productList.put(sE.getProduct(),sE.quantity);
        }
        return productList;
    }

    public List<Product> findByName(String name) throws ShopIsClosedException{
        if(!isOpen()){
            throw new ShopIsClosedException();
        }
        List<Product> found = new ArrayList<>();
        for(ShopEntry sEntry : products.values()){
            if(sEntry.getProduct().getName().equals(name)){
                found.add(sEntry.getProduct());
            }
        }
        return found;
    }

    public boolean hasProduct(Product product, int quantity, float price){
        for(ShopEntry sEntry : products.values()){
            if(sEntry.getProduct() == product && sEntry.getQuantity()==quantity && sEntry.getPrice()==price){
                return true;
            }
        }
        return false;
    }

    public void addNewProduct(Product product,int quantity,float price) throws NoSuchProductException, ShopIsClosedException{
        products.put(product.getBarcode(),new ShopEntry(product,quantity,price));
    }

    public void addProduct(long barcode, int quantity){
            for(ShopEntry entry : products.values()){
                if (entry.getProduct().getBarcode()==barcode){
                    entry.setQuantity(entry.getQuantity()+quantity);
                }
        }
    }

    public List<Product> buyProduct(long barcode,int quantity)throws NoSuchProductException, ShopIsClosedException{
        if(!isOpen()){
            throw new ShopIsClosedException();
        }
        List<Product> returnList = new ArrayList<>();
        for(ShopEntry entry : products.values()){
            if(barcode==entry.getProduct().getBarcode()){
                entry.setQuantity(entry.getQuantity()-quantity);
                for(int i=0;i<quantity;i++){
                    returnList.add(entry.getProduct());
                }
            }

        }
        if(returnList.size()!=0){
            return returnList;
        }
        throw new NoSuchProductException();
    }

    public String getName(){
        return name;
    }



    class ShopEntry{
        private Product product;
        private int quantity;
        private float price;

        ShopEntry(Product product, int quantity, float price){
            this.product=product;
            this.quantity=quantity;
            this.price=price;
            getProduct().price=price;
        }

        Product getProduct(){
            return product;
        }



        int getQuantity(){
            return quantity;
        }

        void setQuantity(int quantity){
            this.quantity=quantity;
        }



        float getPrice(){
            return price;
        }



        public String toString(){
            String result="";
            result+="Quantity: "+Integer.toString(quantity)+"\n Price: "+Float.toString(price);
            return result;
        }



    }
}
