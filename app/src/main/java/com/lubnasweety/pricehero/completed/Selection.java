package com.lubnasweety.pricehero.completed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lubnasweety.pricehero.R;

public class Selection extends AppCompatActivity {

    private Button buyerButton;
    private Button sellerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        buyerButton = (Button) findViewById(R.id.buyerButton);
        sellerButton = (Button) findViewById(R.id.sellerButton);

        View.OnClickListener goToHomepage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Selection.this,Homepage.class));
            }
        };
        buyerButton.setOnClickListener(goToHomepage);


        View.OnClickListener goToSellerHome = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Selection.this,SellerHome.class));
            }
        };
        sellerButton.setOnClickListener(goToSellerHome);
    }
}
