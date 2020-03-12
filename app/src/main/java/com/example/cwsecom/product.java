package com.example.cwsecom;

public class product {
    public int id;
    public String productname,brand,price,image;

    public product(int id, String productname, String brand, String price, String image) {
        this.id = id;
        this.productname = productname;
        this.brand = brand;
        this.price = price;
        this.image = image;
    }
}
