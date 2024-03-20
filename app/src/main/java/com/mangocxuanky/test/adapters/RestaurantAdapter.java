package com.mangocxuanky.test.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mangocxuanky.test.R;
import com.mangocxuanky.test.databinding.RestaurantItemBinding;
import com.mangocxuanky.test.models.Restaurant;

/**
 * Adapter for the list of restaurants.
 * This adapter connects the data with the RecyclerView that displays the list.
 */
public class RestaurantAdapter extends ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder> {

    private final OnItemClickListener listener;

    public RestaurantAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(RestaurantItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = getItem(position);
        holder.bind(restaurant, listener);
    }

    /**
     * ViewHolder for restaurant items. All work is done by binding class
     */
    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private final RestaurantItemBinding binding;

        public RestaurantViewHolder(RestaurantItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Restaurant restaurant, OnItemClickListener listener) {
            binding.restaurantItemName.setText(restaurant.getPlaceName());
            binding.restaurantItemRating.setText(String.valueOf(restaurant.getRatingValue()));
            binding.restaurantDish.setText(restaurant.getDishName());
            binding.restaurantItemRatingcount.setText(binding.getRoot().getContext().getString(R.string.rating_count_format, restaurant.getRatingCount()));
            binding.restaurantItemImage.setImageResource(binding
                    .getRoot().getContext().getResources()
                    .getIdentifier(restaurant.getPhoto(), "drawable",
                    binding.getRoot().getContext().getPackageName()
            ));
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(restaurant));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant);
    }

    private static final DiffUtil.ItemCallback<Restaurant> DIFF_CALLBACK = new DiffUtil.ItemCallback<Restaurant>() {
        @Override
        public boolean areItemsTheSame(@NonNull Restaurant oldItem, @NonNull Restaurant newItem) {
            return oldItem.getPlaceName().equals(newItem.getPlaceName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Restaurant oldItem, @NonNull Restaurant newItem) {
            return oldItem.equals(newItem);
        }
    };
}