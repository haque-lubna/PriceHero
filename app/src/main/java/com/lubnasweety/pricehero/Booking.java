package com.lubnasweety.pricehero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lubnasweety.pricehero.backEnd.DataHelper;
import com.lubnasweety.pricehero.backEnd.Shop;

public class Booking extends AppCompatActivity {
    int bookingComplete = 1;
    int bookingFailed = 0;

    private Button orderButton;
    private EditText quantityText;
    DataHelper dataHelper;
    Integer availableQuantity;
    Integer productLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        orderButton = (Button) findViewById(R.id.orderButton);
        quantityText = (EditText) findViewById(R.id.quantityText);
        dataHelper = DataHelper.getInstance();

        Shop shop = (Shop) getIntent().getSerializableExtra("shop");
        String key = getIntent().getStringExtra("key");
        String productName = getIntent().getStringExtra("productName");
        String productCategory = getIntent().getStringExtra("productCategory");

        availableQuantity = Integer.parseInt(shop.getProductQuantity());
        productLeft = availableQuantity;


        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityText.getText().toString() == null || quantityText.getText().toString().equals("")) {
                    Toast.makeText(Booking.this, "Enter the amount of product you need...", Toast.LENGTH_LONG).show();
                    return;
                }

                Integer neededQuantity = Integer.parseInt(quantityText.getText().toString());

                if (availableQuantity < neededQuantity) {
                    Toast.makeText(Booking.this, "Enough products not available...", Toast.LENGTH_LONG).show();
                    return;
                }

                productLeft = productLeft - neededQuantity;
                String productLeftString = String.valueOf(productLeft);

                Toast.makeText(Booking.this, productLeftString, Toast.LENGTH_LONG).show();


                if (productLeft.equals(0)) {

                    dataHelper.getDatabase().child("products").child(productCategory).child(productName).child("shops").child(key).setValue(null);

                }
                else {
                    shop.setProductQuantity(productLeftString);
                    Toast.makeText(Booking.this, productCategory + " " + productName + " " + shop.getProductQuantity(), Toast.LENGTH_LONG).show();
                    dataHelper.getDatabase().child("products").child(productCategory).child(productName).child("shops").child(key).setValue(shop);
                }

            }
        });

    }
}
