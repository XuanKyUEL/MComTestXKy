package com.mangocxuanky.test.activities;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mangocxuanky.test.R;
import com.mangocxuanky.test.adapters.RestaurantAdapter;
import com.mangocxuanky.test.databinding.ActivityRestaurantsBinding;
import com.mangocxuanky.test.models.Restaurant;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantsActivity extends AppCompatActivity {

    ActivityRestaurantsBinding binding;

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
        getData();
//        loadRestaurants();
        setActionBar(binding.toolbar);
    }

//    private void loadRestaurants() {
//        String category = getIntent().getStringExtra("category");
//        List<Restaurant> restaurants = getRestaurants(category);
//
//        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this, restaurants);
//        binding.listViewRes.setAdapter((ListAdapter) restaurantAdapter);
//    }
//
//    private List<Restaurant> getRestaurants(String category) {
//        AssetManager assetManager = getAssets();
//        try (InputStream is = assetManager.open("data.json")) {
//            Gson gson = new Gson();
//            Type type = new TypeToken<Map<String, List<Restaurant>>>(){}.getType();
//            Map<String, List<Restaurant>> data = gson.fromJson(new InputStreamReader(is), type);
//
//            if (data.containsKey(category)) {
//                return data.get(category);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }

    private void getData() {
        String category = getIntent().getStringExtra("category");

        if ("Lunch Box".equals(category)) {
            binding.titleRes.setText("RICE");
        } else if ("Noodle".equals(category)) {
            binding.titleRes.setText("Noodle");
        }
    }



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
}