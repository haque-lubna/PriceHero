package com.lubnasweety.pricehero.backEnd;

import com.lubnasweety.pricehero.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Asus on 11/23/2017.
 */

public class DataHelper {
    HashMap<productDetails, ArrayList<DetailEachShop>> data = new HashMap<>();
    ArrayList<productDetails> products = new ArrayList<productDetails>();
    ArrayList<DetailEachShop> product1List;
    //ArrayList<DetailEachShop> product2List;


    public ArrayList<DetailEachShop> getShopList(productDetails details) {
        //return data.get(details);
        return product1List;
    }

    public DataHelper() {

        productDetails product4 = new productDetails("Radhuni Mustard Oil", "Radhuni", R.drawable.oil);
        productDetails product5 = new productDetails("Basmoti white rice", "Basmoti", R.drawable.rice);
        productDetails product6 = new productDetails("Mirzapur Tea", "Mirzapur", R.drawable.teapack);

        productDetails product7 = new productDetails("Camera", "Canon", R.drawable.camera);
        productDetails product8 = new productDetails("Laptop", "HP", R.drawable.laptop);
        productDetails product9 = new productDetails("Television", "Walton", R.drawable.tv);

        productDetails product1 = new productDetails("Necless", "PC chondro", R.drawable.necless);
        productDetails product2 = new productDetails("Angti", "Aunjoli", R.drawable.angti);
        productDetails product3 = new productDetails("Bangle", "shornali", R.drawable.bangle);

        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);


        product1List = new ArrayList<DetailEachShop>();
        //product2List = new ArrayList<DetailEachShop>();

        product1List.add(new DetailEachShop(4000, "Aundrila Jewelers", "Ambarkhana", "10% off", 20));
        product1List.add(new DetailEachShop(4100, "Touch of Gold", "Nehari para", "No offer", 35));
        product1List.add(new DetailEachShop(4200, "Diamond Sea", "Tilagor Point", "No offer", 30));
        product1List.add(new DetailEachShop(4250, "Jewelery Palace", "Majumdari, Ambarkhana", "No offer", 3));


//        product2List.add(new DetailEachShop(380, "Lubna Store", "Ambarkhana", "10% offer", 20));
//        product2List.add(new DetailEachShop(400, "Sweety Store", "Nehari para", "No offer", 35));
//        product2List.add(new DetailEachShop(410, "Nammi Store", "Tilagor Point", "No offer", 30));
//        product2List.add(new DetailEachShop(390, "Eshita Store", "Majumdari, Ambarkhana", "No offer", 3));


        data.put(product1, product1List);
        data.put(product2, product1List);
        data.put(product3, product1List);
        data.put(product4, product1List);
        data.put(product5, product1List);
        data.put(product6, product1List);
        data.put(product7, product1List);
        data.put(product8, product1List);
        data.put(product9, product1List);



        //grocery activity
        productDetails grocery1 = new productDetails("Radhuni Masturd Oil","Radhuni",R.drawable.oil);
        productDetails grocery2 = new productDetails("Teapack","Ispahani",R.drawable.teapack);
        productDetails grocery3 = new productDetails("Atta","Ashirbad",R.drawable.ata);
        productDetails grocery4 = new productDetails("Biscuit","Mr.Cookies",R.drawable.biscuit);
        productDetails grocery5 = new productDetails("Tea","Taja",R.drawable.tea);
        productDetails grocery6 = new productDetails("Rice","Basmoti",R.drawable.rice);
        productDetails grocery7 = new productDetails("Fortune Rice","Fortune",R.drawable.fortunerice);
        productDetails grocery8 = new productDetails("Milk","pran",R.drawable.milk);
        productDetails grocery9 = new productDetails("Masturd oil","Suresh",R.drawable.sureshoil);

    }


    public static String getCompany(String name) {
        if(name.equals("Radhuni Mustard Oil")) return "Radhuni";
        else if(name.equals("Basmoti white rice")) return "Basmoti";
        else if(name.equals("Mirzapur Tea")) return "Mirzapur";

        else if(name.equals("Camera")) return "Canon";
        else if(name.equals("Laptop")) return "HP";
        else if(name.equals("Television")) return "Walton";

        else if(name.equals("Necless")) return "PC chondro";
        else if(name.equals("Angti")) return "Aunjoli";
        else if(name.equals("Bangle")) return "shornali";
        else return " ";
    }

    public static String[] getNames(String category) {
        String[] names;
        if (category.equals("ornaments")) {
            names = new String[]{
                    "Necless",
                    "Angti",
                    "Bangle"
            };
        } else if (category.equals("grocery")) {
            names = new String[]{
                    "Radhuni Mustard Oil",
                    "Basmoti white rice",
                    "Mirzapur Tea"
            };
        } else if (category.equals("electronics")) {
            names = new String[]{
                    "Camera",
                    "Laptop",
                    "Television"
            };
        } else {
            names = new String[]{
                    "Necless",
                    "Finger Ring",
                    "Bangle"
            };
        }
        return names;
    }

    public static int[] getImages(String category) {
        int[] images;
        if (category.equals("ornaments")) {
            images = new int[]{
                    R.drawable.necless,
                    R.drawable.angti,
                    R.drawable.bangle
            };

        } else if (category.equals("grocery")) {
            images = new int[]{
                    R.drawable.oil,
                    R.drawable.rice,
                    R.drawable.teapack
            };
        } else if (category.equals("electronics")) {
            images = new int[]{
                    R.drawable.camera,
                    R.drawable.laptop,
                    R.drawable.tv
            };
        } else {
            images = new int[]{
                    R.drawable.necless,
                    R.drawable.angti,
                    R.drawable.bangle
            };

        }

        return images;
    }


    //grocery method

    public static String getCompanyGrocery(String name) {
        if (name.equals("Radhuni Mustard Oil")) return "Radhuni";
        else if (name.equals("Teapack")) return "Ispahani";
        else if (name.equals("Atta")) return "Ashirbad";

        else if(name.equals("Biscuit")) return "Mr.Cookies";
        else if(name.equals("Tea")) return "Taja";
        else if(name.equals("Rice")) return "Basmoti";

        else if(name.equals("Fortune Rice")) return "Fortune";
        else if(name.equals("Milk")) return "pran";
        else if(name.equals("Masturd oil")) return "Suresh";
        else return " ";
    }



    public static int[] getImagesGrocery() {
        int[] images;

            images = new int[]{
                    R.drawable.oil,
                    R.drawable.teapack,
                    R.drawable.ata,
                    R.drawable.biscuit,
                    R.drawable.tea,
                    R.drawable.rice,
                    R.drawable.fortunerice,
                    R.drawable.milk,
                    R.drawable.sureshoil

            };

        return images;
    }


    public static String[] getNamesGrocery() {
        String[] names;
            names = new String[]{
                    "Radhuni Masturd Oil",
                    "Teapack",
                    "Atta",
                    "Biscuit",
                    "Tea",
                    "Rice",
                    "Fortune Rice",
                    "Milk",
                    "Masturd oil"


            };

        return names;
    }




}