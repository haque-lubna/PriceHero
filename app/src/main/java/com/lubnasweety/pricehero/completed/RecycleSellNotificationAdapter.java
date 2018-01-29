package com.lubnasweety.pricehero.completed;

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
import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.backEnd.DataHelper;
import com.lubnasweety.pricehero.backEnd.Notification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Asus on 12/21/2017.
 */

class NotificationHolder extends RecyclerView.ViewHolder {
    View view;
    ImageView productImage;
    TextView buyText;
    //TextView buyPhoneNumber;
    Button allow;
    Button deny;

    public NotificationHolder(View itemView) {
        super(itemView);
        view = itemView;
        productImage = itemView.findViewById(R.id.productImage);
        buyText = itemView.findViewById(R.id.notificationText);
        //buyPhoneNumber = itemView.findViewById(R.id.notificationPhone);
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
    String buyerPhone;
    String buyTxt;
    String buyPhone;

    public RecycleSellNotificationAdapter(ArrayList<Notification> notifications, ArrayList<String> notificationKeys, Activity activity) {
        this.notifications = notifications;
        this.notificationKeys = notificationKeys;
        this.activity = activity;
        dataHelper = DataHelper.getInstance();
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.seller_each_notification, null);

        //needed for match_parent
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        NotificationHolder holder = new NotificationHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        Notification notification = notifications.get(position);
        String notificationKey = notificationKeys.get(position);

        //send notification to buyer
        DatabaseReference buyerNotification = dataHelper.getDatabase().child("users").child(notification.getBuyer()).child("notifications").child("buy");
        DatabaseReference pendingNotification = dataHelper.getDatabase().child("users").child(notification.getBuyer()).child("notifications").child("pending");


        Glide.with(activity).load(notification.getImagePath()).into(holder.productImage);

        dataHelper.getDatabase().child("users").child(notification.getBuyer()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                buyerName = dataSnapshot.child("name").getValue(String.class);
                buyerPhone = dataSnapshot.child("phone").getValue(String.class);

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());

                notification.setDate(formattedDate);
                buyTxt = buyerName + " wants to buy " + notification.getProductNeeded() + " " + notification.getProductName() + ". phone number: " +buyerPhone + "\n" +notification.getDate();

                holder.buyText.setText(buyTxt);
                //buyPhone = "Phone Number: "+buyerPhone;
                //holder.buyPhoneNumber.setText(buyerPhone);
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


                        notification.setAcceptState("accepted");

//                        Calendar today = Calendar.getInstance();
//                        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
//                        String date = dateFormat.format(today);
//
//                        notification.setDate(date);

                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = df.format(c.getTime());

                        notification.setDate(formattedDate);

                        buyerNotification.push().setValue(notification);
                        pendingNotification.push().setValue(notification);
                    }
                    else {
                        Toast.makeText(activity, "not enough products available...", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            //the following line removes the notification
            dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("notifications").child("sell").child(notificationKey).setValue(null);
        });

        holder.deny.setOnClickListener(e->{
            //send notification to buyer

            notification.setAcceptState("rejected");

//            Calendar today = Calendar.getInstance();
//            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
//            String date = dateFormat.format(today);
//
//            notification.setDate(date);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            notification.setDate(formattedDate);

            pendingNotification.push().setValue(notification);

            //the following line removes the notification
            dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("notifications").child("sell").child(notificationKey).setValue(null);
        });

    }



    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
