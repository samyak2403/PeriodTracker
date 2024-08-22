package com.arrowwould.periodtracker.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;


public class ClickableScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
    @Override 
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    public ClickableScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
