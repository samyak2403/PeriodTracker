package com.arrowwould.periodtracker.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public abstract class CalendarSliderAdapter extends RecyclerView.Adapter<CalendarSliderAdapter.DateViewHolder> {
    private int currentDatePos;
    private final List<Date> mDates;
    private int mMiddlePosition;
    private int mSelectedPosition;
    private final int themeColor;

    protected abstract void onDateClicked(int i);

    public CalendarSliderAdapter(List<Date> list, int i, RecyclerView recyclerView) {
        this.mSelectedPosition = -1;
        this.mDates = list;
        this.mMiddlePosition = list.size() / 2;
        this.mSelectedPosition = i;
        recyclerView.smoothScrollToPosition(i);
        this.themeColor = new MyThemeHandler().getAppTheme((Activity) recyclerView.getContext()).getThemeColor();
    }

    @Override 
    public DateViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DateViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calendar_slider_layout, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(DateViewHolder dateViewHolder, final int i) {
        dateViewHolder.bind(this.mDates.get(i), i == this.mSelectedPosition);
        dateViewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                CalendarSliderAdapter.this.m122x61915e3d(i, view);
            }
        });
    }

    
    
    public  void m122x61915e3d(int i, View view) {
        onDateClicked(i);
    }

    @Override 
    public int getItemCount() {
        return this.mDates.size();
    }

    public void setSelectedPosition(int i, RecyclerView recyclerView) {
        int i2 = this.mSelectedPosition;
        if (i != i2) {
            this.mSelectedPosition = i;
            if (recyclerView == null || recyclerView.isComputingLayout()) {
                return;
            }
            notifyItemChanged(i2);
            notifyItemChanged(this.mSelectedPosition);
        }
    }

    
    public class DateViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView dateTv;
        private final LinearLayout dateTvBg;
        private final TextView dayTv;

        public DateViewHolder(View view) {
            super(view);
            this.dateTv = (TextView) view.findViewById(R.id.dateTv);
            this.dayTv = (TextView) view.findViewById(R.id.dayTv);
            this.cardView = (CardView) view.findViewById(R.id.cardView);
            this.dateTvBg = (LinearLayout) view.findViewById(R.id.dateTvBg);
        }

        public void bind(Date date, boolean z) {
            String format = new SimpleDateFormat("EEE", Locale.getDefault()).format(date);
            String format2 = new SimpleDateFormat("dd", Locale.getDefault()).format(date);
            this.dayTv.setText(format);
            this.dateTv.setText(format2);
            Utils.setTint(this.dateTvBg, CalendarSliderAdapter.this.themeColor);
            if (z) {
                Utils.setTint(this.dateTvBg, R.color.white);
                this.cardView.setCardBackgroundColor(this.dateTv.getContext().getResources().getColor(CalendarSliderAdapter.this.themeColor));
                TextView textView = this.dayTv;
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.white));
                this.dateTv.setTextColor(this.dayTv.getContext().getResources().getColor(CalendarSliderAdapter.this.themeColor));
                this.cardView.setCardElevation(0.0f);
                return;
            }
            this.dateTv.setTextColor(this.dayTv.getContext().getResources().getColor(R.color.white));
            TextView textView2 = this.dayTv;
            textView2.setTextColor(textView2.getContext().getResources().getColor(R.color.black));
            this.cardView.setCardBackgroundColor(this.dateTv.getContext().getResources().getColor(17170443));
            this.cardView.setCardElevation(10.0f);
        }
    }
}
