package com.lubnasweety.pricehero;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Asus on 12/18/2017.
 */

class CategoryHolder extends RecyclerView.ViewHolder {
    public TextView categoryName;
//    public RecyclerView categoryData;
    public CategoryHolder(View itemView) {
        super(itemView);
        categoryName = (TextView) itemView.findViewById(R.id.categoryName);
//        categoryData = (RecyclerView) itemView.findViewById(R.id.categoryData);
    }

}

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
    ArrayList<String> categories;
    Activity activity;

    public CategoryAdapter(ArrayList<String> categories, Activity activity) {
        this.categories = categories;
        this.activity = activity;
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.each_category_layout, parent, true);
        CategoryHolder holder = new CategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        String nameOfCategory = categories.get(position);
        holder.categoryName.setText(nameOfCategory);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}
