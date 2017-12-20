package com.lubnasweety.pricehero.completed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.backEnd.DataHelper;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {

    RecyclerView categoryList;
    Intent goToItemDetails;
    DataHelper dataHelper;
    ArrayList<String> categories;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_new);

        dataHelper = DataHelper.getInstance();

        //oikhan theke ana
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        goToItemDetails = new Intent(Homepage.this, ItemDetails.class);

        categoryList = (RecyclerView) findViewById(R.id.categoryList);

        categories = new ArrayList<String>();

        categoryList.setAdapter(new CategoryAdapter(categories, Homepage.this));


        DatabaseReference products = dataHelper.getDatabase().child("products");
        products.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categories = new ArrayList<String>();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    categories.add(child.getKey());
                }
                //Toast.makeText(Homepage.this, String.valueOf(categories.size()), Toast.LENGTH_LONG).show();
                categoryList.setAdapter(new CategoryAdapter(categories, Homepage.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);


    }



}
