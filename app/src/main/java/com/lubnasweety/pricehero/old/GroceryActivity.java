package com.lubnasweety.pricehero.old;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lubnasweety.pricehero.R;


//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.GridView;
//import android.widget.Toast;
//
//import com.lubnasweety.pricehero.completed.ItemDetails;
//import com.lubnasweety.pricehero.old.productDetails;
//
public class GroceryActivity extends AppCompatActivity {
//    Intent goToItemDetails;
//    GridView groceryGrid;
//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        Toolbar toolbar=(Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
//
//
//        groceryGrid = (GridView)findViewById(R.id.groceryGrid);
//        //GridAdapterProducts gridAdapterGrocery = new GridAdapterProducts(this, DataHelper.getNamesGrocery(), DataHelper.getImagesGrocery());
//        //groceryGrid.setAdapter(gridAdapterGrocery);
//
//
//        goToItemDetails = new Intent(GroceryActivity.this, ItemDetails.class);
//
//        GridView.OnItemClickListener gridListener = new GridView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                productDetails details = (productDetails) parent.getAdapter().getItem(position);
//                goToItemDetails.putExtra("data", details);
//                startActivity(goToItemDetails);
//            }
//        };
//
//       groceryGrid.setOnItemClickListener(gridListener);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        if(id==R.id.grocery)
//        {
//            Toast.makeText(this,"grocery",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(this,GroceryActivity.class));
//        }
//        else if(id==R.id.formale)
//        {
//            Toast.makeText(this,"cart",Toast.LENGTH_LONG).show();
//            //startActivity(new Intent(this,CartActivity.class));
//        }
//        else if(id==R.id.forfemale)
//        {
//            Toast.makeText(this,"shopping",Toast.LENGTH_LONG).show();
//            // startActivity(new Intent(this,GroceryActivity.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
