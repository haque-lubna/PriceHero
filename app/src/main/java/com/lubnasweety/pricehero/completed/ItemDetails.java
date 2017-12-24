package com.lubnasweety.pricehero.completed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
import com.lubnasweety.pricehero.backEnd.RecyclerShopListAdapter;
import com.lubnasweety.pricehero.backEnd.Shop;

import java.util.ArrayList;

public class ItemDetails extends AppCompatActivity {

    TextView productName;
    TextView productCategory;
    ImageView productImage;
    RecyclerView shopList;
    ArrayList<Shop> shopArrayList;
    ArrayList<String> shopKeyArrayList;
    DatabaseReference shops;

    String name;
    String category;
    String imageAddress;

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    int bookingComplete = 1;
    int bookingFailed = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        DataHelper dataHelper = DataHelper.getInstance();

        productName = (TextView) findViewById(R.id.productName);
        productCategory = (TextView) findViewById(R.id.productCategory);
        productImage = (ImageView) findViewById(R.id.productImage);



        name = getIntent().getStringExtra("productName");
        category = getIntent().getStringExtra("productCategory");
        imageAddress = getIntent().getStringExtra("imageAddress");


        productName.setText(name);
        productCategory.setText(category);
        Glide.with(this).load(imageAddress).into(productImage);



        shopList = (RecyclerView) findViewById(R.id.shopList);

        shopArrayList = new ArrayList<>();

        shops = dataHelper.getDatabase().child("products").child(category).child(name).child("shops");

        shops.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shopArrayList= new ArrayList<>();
                shopKeyArrayList = new ArrayList<>();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    Shop shop = child.getValue(Shop.class);
                    String key = child.getKey();
                    shopArrayList.add(shop);
                    shopKeyArrayList.add(key);
                }

                shopList.setAdapter(new RecyclerShopListAdapter(shopArrayList, shopKeyArrayList, ItemDetails.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        //ArrayList<DetailEachShop> list = d.getShopList(details);

        //ShopListAdapter shopListAdapter = new ShopListAdapter(this, list);

        //shopList.setAdapter(shopListAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){
            if(resultCode == bookingComplete){
                finish();
            }
            else if(resultCode == bookingFailed) {
                Toast.makeText(this,"Booking Failed",Toast.LENGTH_LONG).show();
            }
        }
    }
}
