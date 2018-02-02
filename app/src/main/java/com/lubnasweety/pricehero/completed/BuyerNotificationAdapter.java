package com.lubnasweety.pricehero.completed;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.backEnd.Notification;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Asus on 12/24/2017.
 */

class BuyerNotificationHolder extends  RecyclerView.ViewHolder {
    ImageView productImage;
    TextView notificationText;
    View view;
    public BuyerNotificationHolder(View itemView) {
        super(itemView);
        productImage = itemView.findViewById(R.id.productImage);
        notificationText = itemView.findViewById(R.id.notificationText);
        this.view = itemView;
    }
}

public class BuyerNotificationAdapter extends RecyclerView.Adapter<BuyerNotificationHolder> {
    ArrayList<Notification> notificationArrayList;
    Activity activity;
    public BuyerNotificationAdapter(ArrayList<Notification> notificationArrayList, Activity activity) {
        this.notificationArrayList = notificationArrayList;
        this.activity = activity;
    }

    @Override
    public BuyerNotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.buyer_each_notification, null);

        //needed for match_parent
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        BuyerNotificationHolder holder = new BuyerNotificationHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BuyerNotificationHolder holder, int position) {
        Notification notification = notificationArrayList.get(position);

        Glide.with(activity).load(notification.getImagePath()).into(holder.productImage);
        String currentNotification = "";
        if(notification.getAcceptState().equals("pending")) {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            notification.setDate(formattedDate);
            currentNotification = "Booking request for " + notification.getProductNeeded() + " " + notification.getProductName() + " sent\n" + notification.getDate();
        }
        else if(notification.getAcceptState().equals("accepted")) {
            currentNotification = "Booking request for " + notification.getProductNeeded() + " " + notification.getProductName() + " accepted. collect it within 3 hours\n"+ notification.getDate();
        }
        else if(notification.getAcceptState().equals("rejected")) {
            currentNotification = "Booking request for " + notification.getProductNeeded() + " " + notification.getProductName() + " rejected\n" + notification.getDate() ;
        }
        holder.notificationText.setText(currentNotification);
    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }
}
