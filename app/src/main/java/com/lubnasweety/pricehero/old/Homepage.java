package com.lubnasweety.pricehero.old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lubnasweety.pricehero.R;

public class Homepage extends AppCompatActivity {
ImageView ivOil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_new);



//        ivOil = (ImageView)findViewById(iv_Oil);
//        View.OnClickListener it = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent details = new Intent(Homepage.this,productDetails.class);
//                startActivity(details);
//            }
//        };
//        ivOil.setOnClickListener(it);

    }
}
