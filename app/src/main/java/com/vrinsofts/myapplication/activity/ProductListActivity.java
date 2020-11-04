package com.vrinsofts.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vrinsofts.myapplication.R;
import com.vrinsofts.myapplication.adapter.ProductAdapter;
import com.vrinsofts.myapplication.databinding.ActivityProductListBinding;
import com.vrinsofts.myapplication.model.Product;
import com.vrinsofts.myapplication.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private static final String TAG = ProductListActivity.class.getSimpleName();
    private ActivityProductListBinding binding;
    private ProductAdapter productAdapter;
    private ProductViewModel productViewModel;
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initView();
    }

    private void initView() {
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productAdapter = new ProductAdapter(this);
        productAdapter.setCategory(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvProduct.setLayoutManager(layoutManager);
        binding.rvProduct.setHasFixedSize(true);
        binding.rvProduct.setAdapter(productAdapter);
        //get product list
        productViewModel.getProductList().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productList = products;
                productAdapter.setProductList(productList);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu;this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuByName:
                //get name filter list
                Collections.sort(productList, Product.NAME_A_TO_Z);
                productAdapter.setCategory(false);
                productAdapter.notifyDataSetChanged();
                break;
            case R.id.menuByPrice:
                //get price low to high filter list
                Collections.sort(productList, Product.PRICE_LOW_HIGH);
                productAdapter.setCategory(false);
                productAdapter.notifyDataSetChanged();
                break;
            case R.id.menuByCategory:
                //get category filter list
                Collections.sort(productList, Product.CATEGORY_A_TO_Z);
                productAdapter.setCategory(true);
                productAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}