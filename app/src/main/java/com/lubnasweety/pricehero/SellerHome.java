package com.lubnasweety.pricehero;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SellerHome extends AppCompatActivity implements HomeFragment.HomeFragmentListener {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content,new HomeFragment()).addToBackStack("HomeFragment").commit();
                    return true;
                case R.id.navigation_post:
                    transaction.replace(R.id.content,new PostFragment()).addToBackStack("PostFragment").commit();

                    return true;
                case R.id.navigation_notifications:

                    transaction.replace(R.id.content,new NotificationFragment()).addToBackStack("NotificationFragment").commit();

                  //  startActivity(new Intent(SellerHome.this,NotificationFragment.class));
                    return true;
            }
            return false;
        }

    };


    int logoutComplete = 1;
    int logoutNot = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);

//         Button logout = (Button) findViewById(R.id.logout);
//         logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View v) {
//                Intent i = new Intent(SellerHome.this,login.class);
//                startActivity(i);
//
//
//            }
//        });
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new HomeFragment()).addToBackStack("HomeFragment").commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//
//    }
    @Override
    public void goToEditProfile() {
        Intent goToEditProfile = new Intent(this, EditProfile.class);
        startActivity(goToEditProfile);
    }

    @Override
    public void goToLogout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(SellerHome.this,login.class));
        Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();


    }
}
