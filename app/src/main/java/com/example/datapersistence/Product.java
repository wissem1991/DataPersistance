package com.example.datapersistence;

public class Product {
    private int _id;
    private String _productname;
    private int _quantity;

    public Product(){}

    public  Product(String name, int quantity){
        this._productname = name;
        this._quantity = quantity;
    }

    public  Product(int id, String name, int quantity){
        this._id = id;
        this._productname = name;
        this._quantity = quantity;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getProductName() {
        return _productname;
    }

    public void setProductName(String _productname) {
        this._productname = _productname;
    }

    public int getQuantity() {
        return _quantity;
    }

    public void setQuantity(int _quantity) {
        this._quantity = _quantity;
    }
}
