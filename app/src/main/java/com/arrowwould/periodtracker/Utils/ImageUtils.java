package com.arrowwould.periodtracker.Utils;

import android.graphics.PorterDuff;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;


public class ImageUtils {
    public static void setTint(ImageView imageView, int i) {
        imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), i), PorterDuff.Mode.SRC_IN);
    }
}
