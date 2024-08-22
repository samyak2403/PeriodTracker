package com.arrowwould.periodtracker.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Activities.ReadBlogActivity;
import com.arrowwould.periodtracker.Model.Blog;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.Utils.Utils;

import java.util.List;


public class InnerArticlesAdapter extends RecyclerView.Adapter<InnerArticlesAdapter.ViewHolder> {
    Activity activity;
    List<Blog> blogList;

    public InnerArticlesAdapter(List<Blog> list, Activity activity) {
        this.blogList = list;
        this.activity = activity;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inner_articles_item, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        int reid = Utils.getResId( this.blogList.get(i).getImgPath(), activity);
        BitmapFactory.decodeResource(this.activity.getResources(), reid, options);
        int min = Math.min(options.outWidth / ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, options.outHeight / ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        options.inJustDecodeBounds = false;
        options.inSampleSize = min;
        options.inPurgeable = true;
        viewHolder.imageView.setImageBitmap(BitmapFactory.decodeResource(this.activity.getResources(),reid, options));
        viewHolder.headingTv.setText(this.blogList.get(i).getHeading());
        String body = this.blogList.get(i).getBody();
        if (body != null) {
            try {
                if (body.startsWith("<h1>") && !body.endsWith("</h1>")) {
                    body = body.split("</h1>")[1];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            viewHolder.descTv.setText(Utils.htmlToText(body));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                InnerArticlesAdapter.this.m130x700461cf(i, view);
            }
        });
    }

    
    
    public  void m130x700461cf(int i, View view) {
        Intent intent = new Intent(this.activity, ReadBlogActivity.class);
        intent.putExtra("heading", this.blogList.get(i).getHeading());
        intent.putExtra("imgRes", this.blogList.get(i).getImgPath());
        intent.putExtra("body", this.blogList.get(i).getBody());
        intent.putExtra("color", this.blogList.get(i).getColor());
        intent.putExtra("dark", this.blogList.get(i).isDark());
        this.activity.startActivity(intent);
        this.activity.finish();
    }

    @Override 
    public int getItemCount() {
        return this.blogList.size();
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descTv;
        TextView headingTv;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.headingTv = (TextView) view.findViewById(R.id.headingTv);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.descTv = (TextView) view.findViewById(R.id.descTv);
        }
    }
}
