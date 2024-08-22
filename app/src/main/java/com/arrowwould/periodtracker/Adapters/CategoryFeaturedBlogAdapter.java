package com.arrowwould.periodtracker.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Activities.ReadBlogActivity;
import com.arrowwould.periodtracker.Model.CategoryFeaturedBlog;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.Utils.Utils;

import java.util.List;


public class CategoryFeaturedBlogAdapter extends RecyclerView.Adapter<CategoryFeaturedBlogAdapter.ViewHolder> {
    Activity activity;
    List<CategoryFeaturedBlog> blogList;
    private int lastPosition = -1;

    public CategoryFeaturedBlogAdapter(List<CategoryFeaturedBlog> list, Activity activity) {
        this.blogList = list;
        this.activity = activity;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_featured_blog_item, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        CategoryFeaturedBlog categoryFeaturedBlog = this.blogList.get(i);
        viewHolder.headingTv.setText(categoryFeaturedBlog.getDetail());
        viewHolder.imageView.setImageResource(Utils.setImage(categoryFeaturedBlog.getImgPath()));

        if (this.blogList.get(i).isDark()) {
            viewHolder.headingTv.setTextColor(this.activity.getResources().getColor(R.color.white));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                CategoryFeaturedBlogAdapter.this.m123xeeec564e(i, view);
            }
        });
    }

    
    
    public  void m123xeeec564e(int i, View view) {
        Intent intent = new Intent(this.activity, ReadBlogActivity.class);
        intent.putExtra("heading", this.blogList.get(i).getHeading());
        intent.putExtra("imgRes", this.blogList.get(i).getImgPath());
        intent.putExtra("body", this.blogList.get(i).getBody());
        intent.putExtra("title", this.blogList.get(i).getDetail());
        intent.putExtra("categories", true);
        intent.putExtra("color", "#FFFFFF");
        intent.putExtra("dark", this.blogList.get(i).isDark());
        this.activity.startActivity(intent);
        Activity activity = this.activity;
        if (activity instanceof ReadBlogActivity) {
            activity.finish();
        }
    }

    @Override 
    public int getItemCount() {
        return this.blogList.size();
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView headingTv;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.headingTv = (TextView) view.findViewById(R.id.headingTv);
            this.cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }
}
