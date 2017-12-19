package com.lubnasweety.pricehero.completed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.backEnd.Product;

import java.util.ArrayList;

/**
 * Created by Asus on 12/18/2017.
 */

class ItemHolder extends RecyclerView.ViewHolder {
    TextView productName;
    ImageView productImage;
    View view;
    public ItemHolder(View itemView) {
        super(itemView);
        productName = (TextView) itemView.findViewById(R.id.productName);
        productImage = (ImageView) itemView.findViewById(R.id.productImage);
        view = itemView;
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
        Product product = productList.get(position); //the current product at index position


        holder.productName.setText(product.getProductName());
        Glide.with(holder.productImage.getContext()).load(product.getImageAddress()).into(holder.productImage);

        holder.view.setOnClickListener(e->{
            Intent goToItemDetails = new Intent(holder.view.getContext(), ItemDetails.class);
            goToItemDetails.putExtra("productName", product.getProductName());
            goToItemDetails.putExtra("productCategory", product.getProductCategory());
            goToItemDetails.putExtra("imageAddress", product.getImageAddress());

            Context context = holder.view.getContext();
            context.startActivity(goToItemDetails);

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
