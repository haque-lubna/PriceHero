package com.lubnasweety.pricehero;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lubnasweety.pricehero.backEnd.DataHelper;
import com.lubnasweety.pricehero.backEnd.Notification;

import java.util.ArrayList;

/**
 * Created by Asus on 12/21/2017.
 */

class NotificationHolder extends RecyclerView.ViewHolder {
    View view;
    ImageView productImage;
    TextView buyText;
    Button allow;
    Button deny;

    public NotificationHolder(View itemView) {
        super(itemView);
        view = itemView;
        productImage = itemView.findViewById(R.id.productImage);
        buyText = itemView.findViewById(R.id.buyText);
        allow = itemView.findViewById(R.id.allow);
        deny = itemView.findViewById(R.id.deny);
    }
}

public class RecycleSellNotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {
    ArrayList<Notification> notifications;
    ArrayList<String> notificationKeys;
    Activity activity;
    DataHelper dataHelper;
    String buyerName;
    String buyTxt;

    public RecycleSellNotificationAdapter(ArrayList<Notification> notifications, ArrayList<String> notificationKeys, Activity activity) {
        this.notifications = notifications;
        this.notificationKeys = notificationKeys;
        this.activity = activity;
        dataHelper = DataHelper.getInstance();
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.seller_each_notification, null);
        NotificationHolder holder = new NotificationHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        Notification notification = notifications.get(position);
        String notificationKey = notificationKeys.get(position);

        Glide.with(activity).load(notification.getImagePath()).into(holder.productImage);

        dataHelper.getDatabase().child("users").child(notification.getBuyer()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buyerName = dataSnapshot.getValue(String.class);
                buyTxt = buyerName + " want to buy " + notification.getProductNeeded() + " " + notification.getProductName();
                holder.buyText.setText(buyTxt);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        holder.allow.setOnClickListener(e->{
            DatabaseReference available = dataHelper.getDatabase().child("products").child(notification.getProductCategory()).child(notification.getProductName()).child("shops").child(notification.getShopKey()).child("productQuantity");
            available.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String availableProducts = dataSnapshot.getValue(String.class);
                    Integer avail = Integer.parseInt(availableProducts);
                    if(avail>=notification.getProductNeeded()) {
                        Integer currentAvailable = avail - notification.getProductNeeded();
                        available.setValue(String.valueOf(currentAvailable));

                        //send notification to buyer
                    }
                    else {
                        Toast.makeText(activity, "not enough products available...", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("notifications").child("sell").child(notificationKey).setValue(null);
        });

        holder.deny.setOnClickListener(e->{
            dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("notifications").child("sell").child(notificationKey).setValue(null);
        });

    }



    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
