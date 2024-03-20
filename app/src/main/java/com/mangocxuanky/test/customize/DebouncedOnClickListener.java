package com.mangocxuanky.test.customize;

import android.view.View;

public abstract class DebouncedOnClickListener implements View.OnClickListener {
    private static final long DEFAULT_DEBOUNCE_TIME = 1500; // in milliseconds
    private long lastClickTime;

    @Override
    public final void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > DEFAULT_DEBOUNCE_TIME) {
            lastClickTime = currentTime;
            onDebouncedClick(v);
        }
    }

    public abstract void onDebouncedClick(View v);
}
