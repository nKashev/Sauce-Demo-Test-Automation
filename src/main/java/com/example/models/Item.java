package com.example.models;

public class Item {
    private String name;
    private String description;
    private String price;

    // Constructor
    public Item(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getDataTestId() {
        return name.toLowerCase().replace(" ", "-");
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}