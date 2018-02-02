package com.lubnasweety.pricehero;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lubnasweety.pricehero.backEnd.Notification;

import java.util.ArrayList;

/**
 * Created by Tanmoy Krishna Das on 2/2/2018.
 */

class BuyerCartViewHolder extends  RecyclerView.ViewHolder {
    TextView productPrice;
    TextView storeName;
    TextView storeLocation;
    TextView productAvailable;
    TextView productOffer;
    ImageView productImage;
    TextView productDescription;
    Button purchaseCompleted;
    TextView bookingAcceptedTime;
    TextView purchaseDeadlineTime;
    View view;

    public BuyerCartViewHolder(View itemView) {
        super(itemView);
        productPrice = itemView.findViewById(R.id.price_each);
        storeName = itemView.findViewById(R.id.store_name_each);
        storeLocation = itemView.findViewById(R.id.store_location_each);
        productAvailable = itemView.findViewById(R.id.needed_each);
        productOffer = itemView.findViewById(R.id.offer_each);
        purchaseCompleted = itemView.findViewById(R.id.purchaseCompleted);
        productImage = itemView.findViewById(R.id.productImage);
        productDescription = itemView.findViewById(R.id.productDescription);
        bookingAcceptedTime = itemView.findViewById(R.id.bookingAcceptedTime);
        purchaseDeadlineTime = itemView.findViewById(R.id.purchaseDeadlineTime);
        view = itemView;
    }
}

class BuyerCartAdapter extends RecyclerView.Adapter<BuyerCartViewHolder> {
    ArrayList<Notification> cartArrayList;
    Cart cart;

    public BuyerCartAdapter(ArrayList<Notification> cartArrayList, Cart cart) {
        this.cartArrayList = cartArrayList;
        this.cart=cart;
    }

    @Override
    public BuyerCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = cart.getLayoutInflater();
        View view = inflater.inflate(R.layout.buyer_each_cart, null);

        //needed for match_parent
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        BuyerCartViewHolder holder = new BuyerCartViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BuyerCartViewHolder holder, int position) {
        Notification notification = cartArrayList.get(position);
        Glide.with(cart).load(notification.getImagePath()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }
}
