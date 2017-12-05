package com.lubnasweety.pricehero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lubnasweety.pricehero.backEnd.DataHelper;
import com.lubnasweety.pricehero.backEnd.DetailEachShop;
import com.lubnasweety.pricehero.backEnd.productDetails;

import java.util.ArrayList;

public class ItemDetails extends AppCompatActivity {
    productDetails details;
    TextView productName;
    TextView productCompany;
    ImageView productImage;
    ListView shopList;

    int bookingComplete = 1;
    int bookingFailed = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        details = (productDetails) getIntent().getSerializableExtra("data");

        productName = (TextView) findViewById(R.id.productName);
        productCompany = (TextView) findViewById(R.id.productCompany);
        productImage = (ImageView) findViewById(R.id.productImage);


        productName.setText(details.getProductName());
        productCompany.setText(details.getCompanyName());
        productImage.setImageResource(details.getImagetName());


        shopList = (ListView) findViewById(R.id.shopList);

        DataHelper d = new DataHelper();

        ArrayList<DetailEachShop> list = d.getShopList(details);

        ShopListAdapter shopListAdapter = new ShopListAdapter(this, list);

        shopList.setAdapter(shopListAdapter);


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
