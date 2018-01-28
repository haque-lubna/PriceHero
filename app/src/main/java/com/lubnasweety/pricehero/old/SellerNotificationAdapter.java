package com.lubnasweety.pricehero.old;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lubnasweety.pricehero.R;

/**
 * Created by Asus on 11/30/2017.
 */

public class SellerNotificationAdapter extends BaseAdapter {


    private String[] buyerName;
   //private String[] buyerPhone;
    private Integer[] productImage;
    private String[] productID;
    private Activity context;
    LayoutInflater layoutInflater;


    public SellerNotificationAdapter(Activity context, String[] buyerName, Integer[] productImage, String[] productID) {
        this.buyerName = buyerName;
        this.productImage = productImage;
        this.productID = productID;
        this.context = context;
    }


//    public SellerNotificationAdapter(String[] buyerName, String[] buyerPhone, Integer[] productImage, String[] productID, Activity context) {
//        this.buyerName = buyerName;
//        this.buyerPhone = buyerPhone;
//        this.productImage = productImage;
//        this.productID = productID;
//        this.context = context;
//    }

    @Override
    public int getCount() {
        return buyerName.length;
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

        layoutInflater = context.getLayoutInflater();
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.seller_each_notification,null);
        }

        TextView buyername = (TextView) convertView.findViewById(R.id.notificationText);
       // TextView buyerphone = (TextView) convertView.findViewById(R.id.notificationPhone);
        ImageView productimage = (ImageView) convertView.findViewById(R.id.productImage);


       buyername.setText(buyerName[position]);
       //buyerphone.setText(buyerPhone[position]);
       productimage.setImageResource(productImage[position]);
        //productid.setText(productID[position]);

return convertView;
    }
}
