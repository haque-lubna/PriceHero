package com.lubnasweety.pricehero.completed;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.backEnd.CategoryAdapter;
import com.lubnasweety.pricehero.backEnd.DataHelper;

import java.util.ArrayList;


public class Homepage extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView categoryList;
    Intent goToItemDetails;
    DataHelper dataHelper;
    ArrayList<String> categories=new ArrayList<>();
    String searchText="";

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

        categoryList.setAdapter(new CategoryAdapter(categories, searchText, Homepage.this));


        DatabaseReference products = dataHelper.getDatabase().child("products");
        products.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categories = new ArrayList<String>();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    categories.add(child.getKey());
                }
                //Toast.makeText(Homepage.this, String.valueOf(categories.size()), Toast.LENGTH_LONG).show();
                categoryList.setAdapter(new CategoryAdapter(categories, searchText, Homepage.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @SuppressLint("RestrictedApi")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);


        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);



        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.logout){
            FirebaseAuth fAuth = FirebaseAuth.getInstance();
            fAuth.signOut();
            finish();
            startActivity(new Intent(Homepage.this,login.class));

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchText=newText;
        categoryList.setAdapter(new CategoryAdapter(categories, searchText, Homepage.this));
        return true;
    }



}
