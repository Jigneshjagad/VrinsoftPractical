package com.vrinsofts.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.vrinsofts.myapplication.R;
import com.vrinsofts.myapplication.databinding.ActivityAddProductBinding;
import com.vrinsofts.myapplication.model.Product;
import com.vrinsofts.myapplication.viewmodel.ProductViewModel;

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = AddProductActivity.class.getSimpleName();
    private ActivityAddProductBinding binding;
    final String[] productCategory = {
            "Select Category", "Clothes", "Electronics", "Beauty"};
    private int categoryPosition = 0;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initView();
    }

    private void initView() {
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        initSpinner();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    saveToDatabase();
                    onClearView();
                    Intent intent = new Intent(AddProductActivity.this, ProductListActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddProductActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveToDatabase() {
        Product product = new Product();
        product.setProduct_name(binding.edtProductName.getText().toString());
        product.setProduct_price(Integer.parseInt(binding.edtProductPrice.getText().toString()));
        product.setProduct_category(productCategory[categoryPosition]);
        productViewModel.insertProduct(product);
    }

    private void initSpinner() {
        binding.spCategory.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, productCategory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spCategory.setAdapter(adapter);
    }

    private boolean isValid() {
        if (binding.edtProductName.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.enter_product_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (binding.edtProductPrice.getText().toString().isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.enter_product_price), Toast.LENGTH_SHORT).show();
            return false;
        } else if (categoryPosition == 0) {
            Toast.makeText(this, getResources().getString(R.string.select_category), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "onItemSelected: " + position);
        categoryPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void onClearView() {
        binding.edtProductName.requestFocus();
        binding.edtProductName.setText("");
        binding.edtProductPrice.setText("");
        categoryPosition = 0;
        binding.spCategory.setSelection(0);
    }
}