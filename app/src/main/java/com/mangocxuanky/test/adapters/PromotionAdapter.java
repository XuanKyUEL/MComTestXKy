package com.mangocxuanky.test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.mangocxuanky.test.R;

import java.util.ArrayList;

public class PromotionAdapter extends ArrayAdapter<Integer> {
    private final Context context;

    private final ArrayList<Integer> values;

    public PromotionAdapter(Context context, ArrayList<Integer> values) {
        super(context, R.layout.promotionblog_item, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridViewItem = inflater.inflate(R.layout.promotionblog_item, null, true);

        ImageView imageView = (ImageView) gridViewItem.findViewById(R.id.promotionblog_item_image);
        int resId = values.get(position);
        imageView.setImageResource(resId);

        return gridViewItem;
    }
}
