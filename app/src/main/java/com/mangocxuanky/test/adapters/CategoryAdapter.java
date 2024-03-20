package com.mangocxuanky.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.mangocxuanky.test.R;
import com.mangocxuanky.test.utils.CONSTANTS;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter<Integer> {
    private final Context context;

    private final List<Integer> values;

    public CategoryAdapter(Context context) {
        super(context, R.layout.category_grid_item, CONSTANTS.CATEGORY_LIST);
        this.context = context;
        this.values = CONSTANTS.CATEGORY_LIST;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridViewItem = inflater.inflate(R.layout.category_grid_item, null, true);

        ImageView imageView = (ImageView) gridViewItem.findViewById(R.id.grid_category_item_image);
        int resId = values.get(position);
        imageView.setImageResource(resId);

        return gridViewItem;
    }
}
