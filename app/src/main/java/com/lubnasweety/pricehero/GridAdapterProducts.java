package com.lubnasweety.pricehero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lubnasweety.pricehero.backEnd.DataHelper;
import com.lubnasweety.pricehero.backEnd.productDetails;

/**
 * Created by Asus on 11/25/2017.
 */

public class GridAdapterProducts extends BaseAdapter {
    Context context;
    private  String[] productNames;
    private  int[] image;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapterProducts(Context context, String[] productNames, int[] image) {
        this.context = context;
        this.productNames = productNames;
        this.image = image;
    }

    @Override
    public int getCount() {
        return productNames.length;
    }

    @Override
    public Object getItem(int position) {
        String name = productNames[position];
        String company = DataHelper.getCompany(name);
        Integer img = image[position];

        productDetails it = new productDetails(name, company, img);
        return it;
    }

    @Override
    public long getItemId(int position) {
        return  0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
            TextView textView = (TextView) view.findViewById(R.id.textview);
            imageView.setImageResource(image[position]);
            textView.setText(productNames[position]);
        }
        return  view;
    }

}
