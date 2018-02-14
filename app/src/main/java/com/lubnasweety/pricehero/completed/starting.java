package com.lubnasweety.pricehero.completed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lubnasweety.pricehero.R;

import static java.lang.Thread.sleep;

public class starting extends AppCompatActivity {
    ImageView imageView;
    View view;
    public static Drawable loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

//        view=this.getWindow().getDecorView();
//        view.setBackgroundResource(R.color.Green);

        loading = getResources().getDrawable( R.drawable.loading001 );

        imageView = (ImageView) findViewById(R.id.imageView);


        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(starting.this,login.class);
                finish();
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
                            finish();
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
