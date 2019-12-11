package com.example.notes;

public class Item {
    private String _Name;
    private String _Price;

    public Item() {
    }

    public Item(String name, String price) {
        _Name = name;
        _Price = price;
    }

    public String getName() {
        return _Name;
    }

    public void setName(String name) {
        _Name = name;
    }

    public String getPrice() {
        return _Price;
    }

    public void setPrice(String price) {
        _Price = price;
    }
}
