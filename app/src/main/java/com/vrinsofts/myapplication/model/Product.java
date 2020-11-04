package com.vrinsofts.myapplication.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "product_name")
    private String product_name;


    @ColumnInfo(name = "product_price")
    private int product_price;

    @ColumnInfo(name = "product_category")
    private String product_category;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public static final Comparator<Product> PRICE_LOW_HIGH = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.product_price - product2.product_price;
        }
    };


    public static final Comparator<Product> NAME_A_TO_Z = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.product_name.compareTo(product2.product_name);
        }
    };

    public static final Comparator<Product> CATEGORY_A_TO_Z = new Comparator<Product>() {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.product_category.compareTo(product2.product_category);
        }
    };
}
