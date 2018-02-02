package com.lubnasweety.pricehero.old;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lubnasweety.pricehero.completed.Booking;
import com.lubnasweety.pricehero.R;

import java.util.ArrayList;

public class ShopListAdapter extends BaseAdapter {

    Context context;
    ArrayList<DetailEachShop> shopList;
    LayoutInflater inflater;


    public ShopListAdapter(Context context, ArrayList<DetailEachShop> shopList) {
        this.context = context;
        this.shopList = shopList;


    }

    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.details_each_shop, null, true);
        }

        DetailEachShop data = shopList.get(position) ;

       TextView priceEach = (TextView) convertView.findViewById(R.id.price_each);
        String priceval = String.valueOf(data.getPrice()) ;
        priceEach.setText("Price: " + priceval + " TK");


        TextView nameEach = (TextView) convertView.findViewById(R.id.store_name_each);
        nameEach.setText(data.getStoreName());

        TextView locationEach = (TextView) convertView.findViewById(R.id.store_location_each);
        locationEach.setText(data.getLocation());

        TextView availableEach = (TextView) convertView.findViewById(R.id.needed_each);
        String availableval = String.valueOf(data.getAvailableProduct());
        availableEach.setText("Total Available : "+availableval);

        TextView offerEach = (TextView) convertView.findViewById(R.id.offer_each);
        offerEach.setText(data.getOffer());

        Button bookEach = (Button) convertView.findViewById(R.id.book_now);
        View.OnClickListener goToOffer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Booking.class);

                Activity parentActivity = (Activity) context;
                parentActivity.startActivityForResult(i, 123);
            }
        };
        bookEach.setOnClickListener(goToOffer);


//        Button rateEach = (Button) convertView.findViewById(R.id.rate_it);
//
//
//        Double price = data.getPrice();
//
//        priceEach.setText(String.valueOf(price) );
//        nameEach.setText(data.getStoreName());
//        locationEach.setText(data.getLocation());
//        availableEach.setText(data.getAvailableProduct());
//        offerEach.setText(data.getOffer());


//
//        data.getPrice();
//        data.getStoreName();
//        data.getLocation();
//        data.getAvailableProduct();
//        data.getOffer();


        return  convertView;

    }

}
