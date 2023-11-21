package com.example.apistore.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Product {
    private String id;
    private String name;
    private String price;
    private String state;
    private String linkToPhoto;
    private String brand;
    private String model;
    private String quantityAvailable;
    private boolean freeShipping;
    private ArrayList<Attribute> attributes;


    public Product(String id, String name, String price, String state, String linkToPhoto, String brand, String model, String quantityAvailable, ArrayList<Attribute> attributes) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.state = state;
        this.linkToPhoto = linkToPhoto;
        this.brand = brand;
        this.model = model;
        this.quantityAvailable = quantityAvailable;
        this.attributes=attributes;
    }

    public Product(String id, String name, String price, String state, String linkToPhoto, boolean freeShipping) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.state = state;
        this.linkToPhoto = linkToPhoto;
        this.freeShipping=freeShipping;
    }


    public void setLinkToPhoto(String linkToPhoto) {
        this.linkToPhoto = linkToPhoto;
    }
}
