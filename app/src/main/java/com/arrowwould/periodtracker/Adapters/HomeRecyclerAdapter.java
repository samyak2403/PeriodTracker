package com.arrowwould.periodtracker.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Model.OvulationData;
import com.arrowwould.periodtracker.R;

import java.util.List;


public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {
    private final Activity activity;
    private final List<OvulationData> list;

    public HomeRecyclerAdapter(List<OvulationData> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_recycler_item, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        OvulationData ovulationData = this.list.get(i);
        int bgColor = ovulationData.getBgColor();
        int color = ovulationData.getColor();
        viewHolder.iconImg.setImageResource(ovulationData.getIcon());
        viewHolder.headingTv.setText(ovulationData.getHeading());
        TextView textView = viewHolder.daysLeftTv;
        textView.setText("(" + ovulationData.getDaysLeft() + " " + this.activity.getResources().getString(R.string.days_left) + ")");
        viewHolder.dateTv.setText(ovulationData.getDate());
        viewHolder.dateTv.setTextColor(this.activity.getResources().getColor(color));
        viewHolder.cardView.setCardBackgroundColor(this.activity.getResources().getColor(bgColor));
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView dateTv;
        TextView daysLeftTv;
        TextView headingTv;
        ImageView iconImg;

        public ViewHolder(View view) {
            super(view);
            this.dateTv = (TextView) view.findViewById(R.id.dateTv);
            this.headingTv = (TextView) view.findViewById(R.id.headingTv);
            this.daysLeftTv = (TextView) view.findViewById(R.id.daysLeftTv);
            this.iconImg = (ImageView) view.findViewById(R.id.iconImg);
            this.cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }
}
