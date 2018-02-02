package com.lubnasweety.pricehero;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lubnasweety.pricehero.backEnd.Notification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

        DatabaseReference shopReference = FirebaseDatabase.getInstance().getReference().child("products").child(notification.getProductCategory()).child(notification.getProductName()).child("shops").child(notification.getShopKey());
        shopReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String productPrice = "Price: " + String.valueOf(dataSnapshot.child("productPrice").getValue(Double.class));
                String storeName = "Store Name: " + dataSnapshot.child("storeName").getValue(String.class);
                String storeLocation = "Store Location: " + dataSnapshot.child("storeLocation").getValue(String.class);
                String productNeeded = "Quantity: " + dataSnapshot.child("productQuantity").getValue(String.class);
                String productOffers = "Offers: " + dataSnapshot.child("productOffers").getValue(String.class);
                String productDescription = dataSnapshot.child("productDescription").getValue(String.class);

                String bookingAcceptedTime = "Booking Accepted: " + notification.getDate();

                holder.productPrice.setText(productPrice);
                holder.storeName.setText(storeName);
                holder.storeLocation.setText(storeLocation);
                holder.productAvailable.setText(productNeeded);
                holder.productOffer.setText(productOffers);
                holder.productDescription.setText(productDescription);

                holder.bookingAcceptedTime.setText(bookingAcceptedTime);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Calendar deadline = Calendar.getInstance();
                    deadline.setTime(df.parse(notification.getDate()));
                    deadline.add(Calendar.HOUR_OF_DAY, 3);

                    String deadlineText = "Purchase Deadline: " + df.format(deadline.getTime());
                    holder.purchaseDeadlineTime.setText(deadlineText);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        holder.purchaseCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase.getInstance().getReference()
                        .child("users").child(uid).child("notifications").child("buy")
                        .child(notification.getPendingKey()).setValue(null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }
}
