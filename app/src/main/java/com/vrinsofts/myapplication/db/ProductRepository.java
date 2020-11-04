package com.vrinsofts.myapplication.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.vrinsofts.myapplication.model.Product;

import java.util.List;

public class ProductRepository {
    private final ProductDao productDao;

    public ProductRepository(Application application) {
        ProductDatabase productDatabase = ProductDatabase.getDatabase(application);
        productDao = productDatabase.productDao();
    }

    public LiveData<List<Product>> getAllProductList() {
        return productDao.getProductList();
    }

    public void insertProduct(Product product) {
        new InsertProduct(productDao).execute(product);
    }

    private static class InsertProduct extends AsyncTask<Product, Void, Void> {

        private ProductDao productDao;

        public InsertProduct(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.insertProduct(products[0]);
            return null;
        }
    }
}
