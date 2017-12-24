package com.lubnasweety.pricehero.backEnd;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lubnasweety.pricehero.R;

import java.util.ArrayList;

/**
 * Created by Asus on 12/18/2017.
 */


class CategoryHolder extends RecyclerView.ViewHolder { //finds all the views in each child
    public TextView categoryName;
    public RecyclerView categoryData;

    public CategoryHolder(View itemView) {
        super(itemView);
        categoryName = (TextView) itemView.findViewById(R.id.categoryName);
        categoryData = (RecyclerView) itemView.findViewById(R.id.categoryData);
    }


}

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
    ArrayList<String> categories;
    Activity activity;
    String searchText;
    ArrayList<Product> itemList;
    public DataHelper dataHelper;

    public CategoryAdapter(ArrayList<String> categories, String searchText, Activity activity) {
        this.categories = categories;
        this.searchText = searchText;
        this.activity = activity;

        dataHelper = DataHelper.getInstance();

    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) { //creates new view
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.each_category_layout, null);

        //needed for match_parent
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);

        CategoryHolder holder = new CategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {  //change in view happens here
        String nameOfCategory = categories.get(position);
        holder.categoryName.setText(nameOfCategory);

        itemList = new ArrayList<Product>();
        holder.categoryData.setAdapter(new ItemAdapter(categories.get(position), activity, itemList));

        DatabaseReference products = dataHelper.getDatabase().child("products").child(nameOfCategory);

        products.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList = new ArrayList<Product>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String name = child.child("name").getValue(String.class);
                    String category = child.child("category").getValue(String.class);
                    String imageAddress = child.child("image").getValue(String.class);

                    if (searchText==null || searchText.equals("") || name.toLowerCase().contains(searchText.toLowerCase())) {
                        Product product = new Product(name, category, imageAddress);
                        itemList.add(product);
                    }

                }

                holder.categoryData.setAdapter(new ItemAdapter(nameOfCategory, activity, itemList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        holder.categoryData.setAdapter();
    }

    @Override
    public int getItemCount() { //number of categories
        return categories.size();
    }


}
