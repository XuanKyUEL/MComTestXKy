package com.mangocxuanky.test.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mangocxuanky.test.R;
import com.mangocxuanky.test.adapters.CategoryAdapter;
import com.mangocxuanky.test.adapters.PromotionAdapter;
import com.mangocxuanky.test.databinding.ActivityMainBinding;
import com.mangocxuanky.test.utils.CONSTANTS;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    CategoryAdapter categoryAdapter;

    ArrayList<Integer> categoryList, promotionList;

    PromotionAdapter promotionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadCategoryAndPromotion();
        categoryControl();
    }

    private void loadCategoryAndPromotion() {
        categoryAdapter = new CategoryAdapter(MainActivity.this);
        binding.gridViewCategory.setAdapter(categoryAdapter);

        promotionAdapter = new PromotionAdapter(MainActivity.this);
        binding.gridViewPromotion.setAdapter(promotionAdapter);
    }

    private void categoryControl() {
        binding.gridViewCategory.setOnItemClickListener((parent, view, position, id) -> {

            if (position == 2 || position == 5) {
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                if (position == 2) {
                    intent.putExtra("category", CONSTANTS.LUNCH_BOX);
                } else {
                    intent.putExtra("category", CONSTANTS.NOODLE);
                }
                startActivity(intent);
            }
        });
    }
}