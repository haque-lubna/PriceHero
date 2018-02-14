package com.lubnasweety.pricehero.backEnd;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lubnasweety.pricehero.R;
import com.lubnasweety.pricehero.completed.Booking;
import com.lubnasweety.pricehero.completed.ItemDetails;
import com.lubnasweety.pricehero.completed.starting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class ShopHolder extends RecyclerView.ViewHolder {
    TextView productPrice;
    TextView storeName;
    TextView storeLocation;
    TextView productAvailable;
    TextView productOffer;
    ImageView productImage;
    TextView productDescription;
    Button bookNow;
    //Button seeMap;
    View view;


    public ShopHolder(View itemView) {
        super(itemView);
        productPrice = itemView.findViewById(R.id.price_each);
        storeName = itemView.findViewById(R.id.store_name_each);
        storeLocation = itemView.findViewById(R.id.store_location_each);
        productAvailable = itemView.findViewById(R.id.needed_each);
        productOffer = itemView.findViewById(R.id.offer_each);
        bookNow = itemView.findViewById(R.id.book_now);
       // seeMap = itemView.findViewById(R.id.seeMap);
        productImage = itemView.findViewById(R.id.productImage);
        productDescription = itemView.findViewById(R.id.productDescription);
        view = itemView;
    }
}

public class RecyclerShopListAdapter extends RecyclerView.Adapter<ShopHolder> {
    ArrayList<Shop> shopArrayList;
    ArrayList<String> shopKeyArrayList;
    Activity activity;
    LayoutInflater inflater;
    Location currentLocation= new Location("Fake Location");
    //Location currentLocation=lastLocation;
    iLatLng sust=new iLatLng(24.9227607,91.8272716);

    static ArrayList<Double> distances = new ArrayList<>();
    ArrayList<Integer> index = new ArrayList<>();


    public static Comparator<Integer> compareByDistance = new Comparator<Integer>() {
        @Override
        public int compare(Integer integer, Integer t1) {
            return distances.get(integer).compareTo(distances.get(t1));
        }
    };




    public RecyclerShopListAdapter(ArrayList<Shop> shopArrayList, ArrayList<String> shopKeyArrayList, Activity activity) {
        this.shopArrayList = shopArrayList;
        this.shopKeyArrayList = shopKeyArrayList;
        this.activity = activity;
        inflater = activity.getLayoutInflater();
        currentLocation.setLatitude(sust.latitude);
        currentLocation.setLongitude(sust.longitude);
        int i=0;
        for(Shop s: shopArrayList) {
            distances.add(distBetweenPoints(currentLocation, s.getLatLng()));
            index.add(i);
            i++;
        }
    }

    public void sortByPrice() {
        Collections.sort(index);
    }

    public void sortByDistance() {
        Collections.sort(index, compareByDistance);
    }

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.details_each_shop, null);

        //the following 2 lines needed for match_parent
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        ShopHolder holder = new ShopHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopHolder holder, int position) {
        position = index.get(position);
        Shop shop = shopArrayList.get(position);
        String key = shopKeyArrayList.get(position);

        holder.productPrice.setText("Price: " + shop.getProductPrice().toString());
        holder.storeName.setText(shop.getStoreName());
        holder.storeLocation.setText( shop.getStoreLocation());
        holder.productAvailable.setText("Available: " + shop.getProductQuantity());
        holder.productOffer.setText("Offer: " + shop.getProductOffers());
        GlideApp.with(activity).load(shop.getImageUrl()).placeholder( starting.loading).into(holder.productImage);
        holder.productDescription.setText(shop.getProductDescription());

        holder.bookNow.setOnClickListener(e->{
            Intent goToBooking = new Intent(activity, Booking.class);
            goToBooking.putExtra("shop",shop);
            goToBooking.putExtra("key", key);
            goToBooking.putExtra("productName", ( (ItemDetails) activity).getName());
            goToBooking.putExtra("productCategory", ( (ItemDetails) activity).getCategory() );
            activity.startActivity(goToBooking);
        });

//       holder.seeMap.setOnClickListener(e->{
//            Intent goToMap = new Intent(activity, MapsActivity.class);
//            activity.startActivity(goToMap);
//        });


    }

    @Override
    public int getItemCount() {

        return shopArrayList.size();
    }
    public double distBetweenPoints(Location origin ,iLatLng dest){
        Location endLocation=new Location("dest");
        endLocation.setLatitude(dest.latitude);
        endLocation.setLongitude(dest.longitude);
        double distance= origin.distanceTo(endLocation);
        return distance;
    }
}
