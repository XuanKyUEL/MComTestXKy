package com.mangocxuanky.test.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mangocxuanky.test.R;
import com.mangocxuanky.test.adapters.CategoryAdapter;
import com.mangocxuanky.test.adapters.PromotionAdapter;
import com.mangocxuanky.test.databinding.ActivityMainBinding;

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
        initCategoryList();
        loadCategoryList();
        initPromotionList();
        loadPromotionList();
    }

    private void initPromotionList() {
        promotionList = new ArrayList<>();
        promotionList.add(R.drawable.chaosuon_promo);
        promotionList.add(R.drawable.comebuy_promo);
        promotionList.add(R.drawable.share_tea_promo);
        promotionList.add(R.drawable.tocotoco_promo);
    }

    private void loadPromotionList() {
        promotionAdapter = new PromotionAdapter(
                MainActivity.this,
                promotionList
        );
        binding.gridViewPromotion.setAdapter(promotionAdapter);
    }

    private void initCategoryList() {
        categoryList = new ArrayList<>();
        categoryList.add(R.drawable.healthy_icon);
        categoryList.add(R.drawable.korean_icon);
        categoryList.add(R.drawable.lunch_box_icon);
        categoryList.add(R.drawable.snack_icon);
        categoryList.add(R.drawable.milk_tea_icon);
        categoryList.add(R.drawable.noodle_icon);
    }

    private void loadCategoryList() {
        categoryAdapter = new CategoryAdapter(
                MainActivity.this,
                categoryList
        );
        binding.gridViewCategory.setAdapter(categoryAdapter);
    }
}