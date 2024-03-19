package com.mangocxuanky.test.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mangocxuanky.test.R;
import com.mangocxuanky.test.models.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private final List<Restaurant> restaurantsLists;
    private final Context context;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        this.context = context;
        this.restaurantsLists = restaurants;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurantsLists.get(position);
        holder.restaurantName.setText(restaurant.getPlaceName());
        holder.dishName.setText(restaurant.getDishName());
        holder.ratingCount.setText(restaurant.getRatingCount());
        // Assuming you have a method to get the drawable resource id by name
        int resId = context.getResources().getIdentifier(restaurant.getPhoto(), "drawable", context.getPackageName());
        holder.restaurantImage.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return restaurantsLists.size();
    }


    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantImage;
        TextView restaurantName,dishName,ratingCount;

        public RestaurantViewHolder(View view) {
            super(view);
            restaurantImage = view.findViewById(R.id.restaurant_item_image);
            restaurantName = view.findViewById(R.id.restaurant_item_name);
            dishName = view.findViewById(R.id.restaurant_dish);
            ratingCount = view.findViewById(R.id.restaurant_item_ratingcount);
        }
    }
}
