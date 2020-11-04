package com.vrinsofts.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vrinsofts.myapplication.databinding.ItemProductBinding;
import com.vrinsofts.myapplication.databinding.ItemProductCategoryBinding;
import com.vrinsofts.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ProductAdapter.class.getSimpleName();
    // View Types
    private static final int ITEM = 0;
    private static final int CATEGORY = 1;

    private boolean isCategory = false;

    private Context context;
    private List<Product> productList = new ArrayList<>();

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                ItemProductBinding productBinding = ItemProductBinding.inflate(inflater, parent, false);
                viewHolder = new ItemViewHolder(productBinding);
                break;
            case CATEGORY:
                ItemProductCategoryBinding itemProductCategoryBinding = ItemProductCategoryBinding.inflate(inflater, parent, false);
                viewHolder = new ItemCategoryViewHolder(itemProductCategoryBinding);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = productList.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.itemProductBinding.setProduct(product);
                itemViewHolder.itemProductBinding.executePendingBindings();
                break;
            case CATEGORY:
                ItemCategoryViewHolder itemCategoryViewHolder = (ItemCategoryViewHolder) holder;
                itemCategoryViewHolder.itemProductCategoryBinding.setProduct(product);
                //hide the same category view
                if (position > 0) {
                    if (productList.get(position).getProduct_category()
                            .equalsIgnoreCase(productList.get(position - 1).getProduct_category())) {
                        itemCategoryViewHolder.itemProductCategoryBinding.txtProductCategory.setVisibility(View.GONE);
                    } else {
                        itemCategoryViewHolder.itemProductCategoryBinding.txtProductCategory.setVisibility(View.VISIBLE);
                    }
                } else {
                    itemCategoryViewHolder.itemProductCategoryBinding.txtProductCategory.setVisibility(View.VISIBLE);
                }
                itemCategoryViewHolder.itemProductCategoryBinding.executePendingBindings();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (isCategory) ? CATEGORY : ITEM;
    }

    public void setCategory(boolean category) {
        isCategory = category;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ItemProductBinding itemProductBinding;

        public ItemViewHolder(@NonNull ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;
        }
    }

    public class ItemCategoryViewHolder extends RecyclerView.ViewHolder {
        ItemProductCategoryBinding itemProductCategoryBinding;

        public ItemCategoryViewHolder(@NonNull ItemProductCategoryBinding itemProductCategoryBinding) {
            super(itemProductCategoryBinding.getRoot());
            this.itemProductCategoryBinding = itemProductCategoryBinding;
        }
    }

}
