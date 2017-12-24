package com.lubnasweety.pricehero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lubnasweety.pricehero.backEnd.DataHelper;
import com.lubnasweety.pricehero.backEnd.Notification;

import java.util.ArrayList;
import java.util.Collections;

public class BuyerNotification extends AppCompatActivity {

    RecyclerView buyerNotification;
    DataHelper dataHelper;
    ArrayList<Notification> notificationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer__notification);
        buyerNotification = findViewById(R.id.buyerNotification);

        dataHelper = DataHelper.getInstance();
        DatabaseReference buyerNotificationDatabse = dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("notifications").child("pending");
        buyerNotificationDatabse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notificationArrayList = new ArrayList<>();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    notificationArrayList.add(child.getValue(Notification.class));
                }
                Collections.reverse(notificationArrayList);
                buyerNotification.setAdapter(new BuyerNotificationAdapter(notificationArrayList, BuyerNotification.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
