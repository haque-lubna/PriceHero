package com.lubnasweety.pricehero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class starting extends AppCompatActivity {
    ImageView imageView;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

//        view=this.getWindow().getDecorView();
//        view.setBackgroundResource(R.color.Green);

        imageView = (ImageView) findViewById(R.id.imageView);


        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(starting.this,login.class);
                startActivity(i);
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1500);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(starting.this,login.class);
                            startActivity(i);
                        }
                    });


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();



    }
}
