package com.lubnasweety.pricehero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lubnasweety.pricehero.backEnd.DataHelper;
import com.lubnasweety.pricehero.backEnd.Notification;

import java.util.ArrayList;
import java.util.Collections;

public class Cart extends AppCompatActivity {
    RecyclerView buyerCart;
    DataHelper dataHelper;
    ArrayList<Notification> cartArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        buyerCart = findViewById(R.id.buyerCart);
        dataHelper = DataHelper.getInstance();

        DatabaseReference cartDatabse = dataHelper.getDatabase().child("users").child(dataHelper.getUid()).child("notifications").child("buy");
        cartDatabse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cartArrayList = new ArrayList<>();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    cartArrayList.add(child.getValue(Notification.class));
                }
                Collections.reverse(cartArrayList);
                buyerCart.setAdapter(new BuyerCartAdapter(cartArrayList, Cart.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
