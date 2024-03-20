package com.mangocxuanky.test.activities;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mangocxuanky.test.R;
import com.mangocxuanky.test.adapters.RestaurantAdapter;
import com.mangocxuanky.test.databinding.ActivityRestaurantsBinding;
import com.mangocxuanky.test.models.Restaurant;
import com.mangocxuanky.test.utils.CONSTANTS;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This activity is responsible for displaying a list of restaurants.
 * It fetches the restaurant data from a local JSON file and displays it in a RecyclerView.
 * When a restaurant item is clicked, a detailed dialog is shown with more information about the restaurant.
 */
public class RestaurantsActivity extends AppCompatActivity {

    ActivityRestaurantsBinding binding;
    boolean isRice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRestaurantsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadRestaurants();
        setActionBar(binding.toolbar);
    }

    /**
     * Load the restaurants data and set up the RecyclerView.
     */
    private void loadRestaurants() {
        String category = getIntent().getStringExtra("category");
        category = fromCategory(category);
        List<Restaurant> restaurants = getRestaurants(category);

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this, restaurants,this::showDetailsDialog);
        binding.recycleViewRes.setAdapter(restaurantAdapter);
        binding.recycleViewRes.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Show a dialog with detailed information about the selected restaurant.
     *
     * @param restaurant The selected restaurant.
     */
    private void showDetailsDialog(Restaurant restaurant) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_detail_res_dialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);

        // set animation
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        // Find the views in the dialog layout
        ImageView dialogImage = dialog.findViewById(R.id.custom_detail_res_dialog_image);
        TextView dialogName = dialog.findViewById(R.id.custom_detail_res_dialog_name);
        TextView dialogRating = dialog.findViewById(R.id.custom_detail_res_dialog_rating);
        TextView dialogPlace = dialog.findViewById(R.id.custom_detail_res_dialog_place);

        // Set the data to the views
        dialogName.setText(restaurant.getPlaceName());
        dialogRating.setText(String.valueOf(restaurant.getRatingValue()));
        dialogPlace.setText(restaurant.getAddress());

        // Set the image resource directly
        String imageName = restaurant.getPhoto();
        int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        dialogImage.setImageResource(resId);

        dialog.show();
    }

    /**
     * Fetch the restaurants data from the local JSON file.
     * @param category The category of the restaurants to fetch.
     * @return A list of restaurants in the specified category.
     */
    private List<Restaurant> getRestaurants(String category) {
        AssetManager assetManager = getAssets();
        try (InputStream is = assetManager.open("data.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<Restaurant>>>(){}.getType();
            Map<String, List<Restaurant>> data = gson.fromJson(new InputStreamReader(is), type);

            if (data.containsKey(category)) {
                return data.get(category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Determine the category from the intent extra and set the title accordingly.
     * @param category The category from the intent extra.
     * @return The determined category.
     */
    private String fromCategory(String category) {
        String cate = getIntent().getStringExtra("category");

        if (CONSTANTS.LUNCH_BOX.equals(cate)) {
            binding.titleRes.setText(CONSTANTS.RICE);
            category = CONSTANTS.LUNCH_BOX;
            isRice = true;
        } else if (CONSTANTS.NOODLE.equals(cate)) {
            binding.titleRes.setText(CONSTANTS.NOODLE);
            category = CONSTANTS.NOODLE;
            isRice = false;
        }
        return category;
    }

    /**
     * Set up the action bar with a custom title and back button.
     * @param toolbar The toolbar to set as the action bar.
     */
    public void setActionBar(@Nullable Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        // set icon arrow
        actionBar.setHomeAsUpIndicator(R.drawable.back_icon);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("Restaurants");
        // Create a TextView programmatically.
        TextView textView = new TextView(this);
        textView.setText("Restaurants");
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(20);

        // Set the TextView as the title.
        actionBar.setCustomView(textView);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    /**
     * Handle the selection of menu items in the action bar.
     * @param item The selected menu item.
     * @return true if the selection was handled, false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}