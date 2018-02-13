package com.codecool.plaza.cmdprog;

import com.codecool.plaza.api.*;


import java.util.*;

public class CmdProgram {
    private List<Product> cart = new ArrayList<>();
    private Plaza plaza;

    private String[] plazaOptions = {"List all shops","Add a new shop","Remove a shop","Enter a shop by name","Open the plaza",
            "Close the plaza","Check if plaza is open or not","leave"};

    private String[] shopOptions = {"List all products","Find products by name","Display the shop's owner","open the shop","close the shop",
    "add new product to the shop","add existing product to shop","buy a product by barcode","go back to plaza"};


    CmdProgram(String[] args){

    }

    public void  run() throws PlazaIsClosedException {
        System.out.println("There isn't a Plaza yet. Would you like to create on ? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        if(input.equals("n")){
            System.out.println("Okay, see you later");
            System.exit(0);
        }else{
            plaza = new PlazaImpl(true);
        }


        input="0";
        while(input!="8") {
            displayPlazaOptions();
            input=scanner.next();
            switch (input) {
                case "1":
                    if(plaza.isOpen()) {
                        for (int i = 0; i < plaza.getShops().size(); i++) {
                            System.out.print("\nname: " + plaza.getShops().get(i).getName() + "\n  Owner: " + plaza.getShops().get(i).getOwner());
                        }
                        System.out.println("\n");
                    }else{
                        System.out.println("The plaza is closed. Open plaza to do that");
                    }
                    break;
                case "2":
                    if(plaza.isOpen()) {
                        System.out.print("Name of the shop: ");
                        String sName = scanner.next();
                        System.out.print("Owner of the shop: ");
                        String sOwner = scanner.next();
                        try {
                            plaza.addShop(new ShopImpl(sName, sOwner));
                        } catch (ShopAlreadyExistsException e) {
                            System.out.println("There's already a shop with that name");
                        }
                    }else{
                        System.out.println("The plaza is closed. Open plaza to do that");
                    }
                    break;
                case "3":
                    if(plaza.isOpen()) {
                        System.out.print("Type in the name of the shop you want to remove: ");
                        String shopToRemove = scanner.next();
                        try {
                            plaza.removeShop(plaza.findShopByName(shopToRemove));
                        } catch (noSuchShopException e) {
                            System.out.println("There isn't a shop with that name");
                        }
                    }else{
                        System.out.println("The plaza is closed. Open plaza to do that");
                    }
                    break;
                case "4":
                    if(plaza.isOpen()) {
                        System.out.print("Which shop would you like to enter ? : ");
                        String shopToEnter = scanner.next();
                        try {
                            ShopImpl shop = (ShopImpl) plaza.findShopByName(shopToEnter);
                            String shopInput = "";
                            while (!shopInput.equals("0")) {
                                displayShopOptions();
                                shopInput = scanner.next();
                                switch (shopInput) {
                                    case "1":
                                        if(shop.isOpen()) {
                                            Map<Product, Integer> tmpList = shop.getAvaliableProducts();
                                            for (Product p : tmpList.keySet()) {
                                                System.out.println("Name: " + p.getName() + "\n  Quantity: " + tmpList.get(p) + "\n  Price: " + p.getPrice());
                                            }
                                        }else{
                                            System.out.println("The shop is closed. Open shop to do that");
                                        }
                                        break;
                                    case "2":
                                        if(shop.isOpen()) {
                                            System.out.print("What product are you looking for ? : ");
                                            String foundProduct = scanner.next();
                                            List<Product> productsByName = new ArrayList<>();
                                            try {
                                                productsByName = shop.findByName(foundProduct);
                                            } catch (ShopIsClosedException e) {
                                                System.out.println("The shop is closed. First you have to open it to do that");
                                            }
                                            for (Product p : productsByName) {
                                                System.out.println("Barcode: " + p.getBarcode() + "\nName: " + p.getName());
                                            }
                                        }else{
                                            System.out.println("The shop is closed. Open shop to do that");
                                        }
                                        break;
                                    case "3":
                                        if(shop.isOpen()) {
                                            System.out.println("The shop's owner : " + shop.getOwner());
                                        }else{
                                            System.out.println("The shop is closed. Open shop to do that");
                                        }
                                        break;
                                    case "4":
                                        shop.open();
                                        break;
                                    case "5":
                                        shop.close();
                                        break;
                                    case "6":
                                        if(shop.isOpen()) {
                                            System.out.println("What kind of product would you like to add ? (food/clothing)");
                                            String productType = scanner.next();
                                            if (productType.equals("food")) {
                                                System.out.print("Barcode: ");
                                                long bCode = scanner.nextLong();
                                                System.out.print("\nManufacturer: ");
                                                String manu = scanner.next();
                                                System.out.print("\nname: ");
                                                String name = scanner.next();
                                                System.out.println("\nCalories: ");
                                                int calories = scanner.nextInt();
                                                System.out.print("\nbestBefore: ");
                                                Date date = new Date();
                                                System.out.print("\nHow many would you like to add ? ");
                                                int quan = scanner.nextInt();
                                                System.out.print("\nFor how much ?");
                                                float price = scanner.nextFloat();

                                                try {
                                                    shop.addNewProduct(new FoodProduct(bCode, manu, name, calories, date, price), quan, price);
                                                } catch (ShopIsClosedException e) {
                                                    System.out.println("The shop is closed. First you have to open it to do that");
                                                } catch (NoSuchProductException l) {
                                                    System.out.println("There's no such product in our store");
                                                }
                                            } else {
                                                System.out.print("Barcode: ");
                                                long bCode = scanner.nextLong();
                                                System.out.print("\nManufacturer: ");
                                                String manu = scanner.next();
                                                System.out.print("\nname: ");
                                                String name = scanner.next();
                                                System.out.println("\nMaterial: ");
                                                String material = scanner.next();
                                                System.out.print("\nType : ");
                                                String type = scanner.next();
                                                System.out.print("\nHow many would you like to add ? ");
                                                int quan = scanner.nextInt();
                                                System.out.print("\nFor how much ?");
                                                float price = scanner.nextFloat();

                                                try {
                                                    shop.addNewProduct(new ClothingProduct(bCode, manu, name, material, type, price), quan, price);
                                                } catch (ShopIsClosedException e) {
                                                    System.out.println("The shop is closed. First you have to open it to do that");
                                                } catch (NoSuchProductException l) {
                                                    System.out.println("There's no such product in our store");
                                                }

                                            }
                                        }else{
                                            System.out.println("The shop is closed. Open shop to do that");
                                        }
                                        break;
                                    case "7":
                                        if(shop.isOpen()) {
                                            System.out.println("What product would you like to add more of ? (barcode)");
                                            long bCode = scanner.nextLong();
                                            System.out.println("How many?");
                                            int quan = scanner.nextInt();
                                            shop.addProduct(bCode, quan);
                                        }else{
                                            System.out.println("The shop is closed. Open shop to do that");
                                        }
                                        break;
                                    case "8":
                                        if(shop.isOpen()) {
                                            System.out.print("Which product would you like to buy ? (barcode): ");
                                            Long bCodeForBuying = scanner.nextLong();
                                            System.out.println("How many of it?");
                                            int quantityForBuying = scanner.nextInt();
                                            try {
                                                List<Product> tmplist = shop.buyProduct(bCodeForBuying, quantityForBuying);
                                                cart.addAll(tmplist);
                                            } catch (ShopIsClosedException e) {
                                                System.out.println("The shop is closed. First you have to open it to do that");
                                            } catch (NoSuchProductException l) {
                                                System.out.println("There's no such product in our store");
                                            }
                                        }else{
                                            System.out.println("The shop is closed. Open shop to do that");
                                        }

                                        break;

                                    case "9":
                                        shopInput = "0";
                                        break;
                                    default:
                                        System.out.println("There's no such command");


                                }

                            }
                        } catch (noSuchShopException e) {
                            System.out.println("There isn't a shop with that name");
                        }
                    }else{
                        System.out.println("The plaza is closed. Open plaza to do that");
                    }


                    break;
                case "5":
                    plaza.open();
                    break;
                case "6":
                    plaza.close();
                    break;
                case "7":
                    if (plaza.isOpen()) {
                        System.out.println("The plaza is open\n");
                    } else {
                        System.out.println("The plaza is closed\n");
                    }
                    break;

                case "8":
                    float totalPrice=0;
                    if (cart.size() != 0) {

                        System.out.println("The content of your cart: ");
                        for (Product p : cart) {
                            System.out.println("Name: " + p.getName());
                            totalPrice+=p.getPrice();
                        }
                        System.out.println("The total amount you'll pay is : "+totalPrice+" $");


                    } else {
                        System.out.println("Your cart is empty");
                    }
                    System.out.println("See you next time!");
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("There's no such command!");

            }


            }
        }















    private void displayPlazaOptions(){
        for(int i=0;i<plazaOptions.length;i++){
            System.out.println((i+1)+" "+plazaOptions[i]);
        }
    }

    private void displayShopOptions(){
        for(int i=0;i<shopOptions.length;i++){
            System.out.println((i+1)+" "+shopOptions[i]);
        }
    }
}
