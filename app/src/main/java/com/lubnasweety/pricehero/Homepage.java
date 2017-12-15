package com.lubnasweety.pricehero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.lubnasweety.pricehero.backEnd.productDetails;
import com.lubnasweety.pricehero.completed.login;

public class Homepage extends AppCompatActivity {

    GridView ornamentsGridView;
    GridView groceryGridView;
    GridView electronicsGridView;
    Intent goToItemDetails;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_new);

        //oikhan theke ana
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        ornamentsGridView = (GridView) findViewById(R.id.ornamentsproducts);
//        GridAdapterProducts gridAdapterOrnaments = new GridAdapterProducts(this, DataHelper.getNames("ornaments"), DataHelper.getImages("ornaments"));
//        ornamentsGridView.setAdapter(gridAdapterOrnaments);

        groceryGridView = (GridView) findViewById(R.id.groceryproducts);
//        GridAdapterProducts gridAdapterGrocery = new GridAdapterProducts(this, DataHelper.getNames("grocery"), DataHelper.getImages("grocery"));
//        groceryGridView.setAdapter(gridAdapterGrocery);

        electronicsGridView = (GridView) findViewById(R.id.electronicsproducts);
//        GridAdapterProducts gridAdapterElectronics = new GridAdapterProducts(this, DataHelper.getNames("electronics"), DataHelper.getImages("electronics"));
//        electronicsGridView.setAdapter(gridAdapterElectronics);

        goToItemDetails = new Intent(Homepage.this, ItemDetails.class);

        GridView.OnItemClickListener gridListener = new GridView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productDetails details = (productDetails) parent.getAdapter().getItem(position);
                goToItemDetails.putExtra("data", details);
                startActivity(goToItemDetails);
            }
        };

        ornamentsGridView.setOnItemClickListener(gridListener);
        groceryGridView.setOnItemClickListener(gridListener);
        electronicsGridView.setOnItemClickListener(gridListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.grocery)
        {
            Toast.makeText(this,"grocery",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,GroceryActivity.class));
        }
        else if(id==R.id.formale)
        {
            Toast.makeText(this,"cart",Toast.LENGTH_LONG).show();
            //startActivity(new Intent(this,CartActivity.class));
        }
        else if(id==R.id.forfemale)
        {
            Toast.makeText(this,"shopping",Toast.LENGTH_LONG).show();
            // startActivity(new Intent(this,GroceryActivity.class));
        }
        else if(id == R.id.logout){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
           startActivity(new Intent(this,login.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
