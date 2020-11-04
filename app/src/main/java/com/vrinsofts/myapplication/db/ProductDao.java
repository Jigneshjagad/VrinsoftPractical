package com.vrinsofts.myapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vrinsofts.myapplication.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("select * from product_table")
    LiveData<List<Product>> getProductList();

}
