package com.lubnasweety.pricehero;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lubnasweety.pricehero.backEnd.Product;

import java.util.ArrayList;

/**
 * Created by Asus on 12/18/2017.
 */

class ItemHolder extends RecyclerView.ViewHolder {
    TextView productName;
    ImageView productImage;
    public ItemHolder(View itemView) {
        super(itemView);
        productName = (TextView) itemView.findViewById(R.id.productName);
        productImage = (ImageView) itemView.findViewById(R.id.productImage);
    }
}

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    String categoryName;
    Activity activity;
    ArrayList<Product> productList;

    public ItemAdapter(String categoryName, Activity activity, ArrayList<Product> productList) {
        this.categoryName = categoryName;
        this.activity = activity;
        this.productList = productList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.single_item, null);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getProductName());
        Glide.with(holder.productImage.getContext()).load(product.getImageAddress()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
