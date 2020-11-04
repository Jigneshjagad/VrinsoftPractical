package com.vrinsofts.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vrinsofts.myapplication.db.ProductRepository;
import com.vrinsofts.myapplication.model.Product;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
    }

    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }

    public LiveData<List<Product>> getProductList() {
        return productRepository.getAllProductList();
    }

}
