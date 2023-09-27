package com.pooria.pooriasyelp;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

// Custom adapter for populating the restaurant list.
public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Business> businesses;

    public ListAdapter(Context context, ArrayList<Business> businesses) {
        this.context = context;
        this.businesses = businesses;
    }

    // Implement the required methods for the adapter.
    @Override
    public int getCount() {
        return businesses.size();
    }

    @Override
    public Object getItem(int position) {
        return businesses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.restaurant_item, null);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.name);
            holder.ratingBar = convertView.findViewById(R.id.rating);
            holder.ratingBar.setIsIndicator(true);
            holder.price = convertView.findViewById(R.id.price);
            holder.type = convertView.findViewById(R.id.type);
            holder.phoneNumber = convertView.findViewById(R.id.phone_number);
            holder.address = convertView.findViewById(R.id.address);
            holder.imageView = convertView.findViewById(R.id.cover_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String displayName = (position + 1) + ". " + businesses.get(position).name;
        holder.name.setText(displayName);
        holder.ratingBar.setRating(businesses.get(position).rating);
        holder.price.setText(businesses.get(position).price);
        if (businesses.get(position).categories != null && !businesses.get(position).categories.isEmpty()) {
            Category category = businesses.get(position).categories.get(0);
            holder.type.setText(category.title);
        }
        holder.phoneNumber.setText(businesses.get(position).phone);

        Location location = businesses.get(position).location;
        if (location.displayAddress != null && !location.displayAddress.isEmpty()) {
            StringBuilder addressStr = new StringBuilder();
            for (String line : location.displayAddress) {
                if (addressStr.length() > 0) {
                    addressStr.append("\n");
                }
                addressStr.append(line);
            }
            holder.address.setText(addressStr.toString());
        }

        Glide.with(context)
                .load(businesses.get(position).url)
                .centerCrop()
                .into(holder.imageView);

        return convertView;
    }


    static class ViewHolder {
        TextView name;
        RatingBar ratingBar;
        TextView price;
        TextView type;
        TextView phoneNumber;
        TextView address;
        ImageView imageView;
    }
}
