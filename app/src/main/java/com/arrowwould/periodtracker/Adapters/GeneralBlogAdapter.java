package com.arrowwould.periodtracker.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Activities.ReadBlogActivity;
import com.arrowwould.periodtracker.Model.Blog;
import com.arrowwould.periodtracker.Model.FeaturedBlog;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GeneralBlogAdapter extends RecyclerView.Adapter<GeneralBlogAdapter.ViewHolder> {
    public static final String TAG = "BlogsTAG";
    private static final int VIEW_TYPE_HORIZONTAL = 1;
    private static final int VIEW_TYPE_VERTICAL = 0;
    Activity activity;
    List<FeaturedBlog> featuredBlogList;
    private int lastPosition = -1;
    List<Blog> list;
    private final boolean mix;
    private final List<String> orList;

    public GeneralBlogAdapter(List<Blog> list, List<FeaturedBlog> list2, List<String> list3, Activity activity, boolean z) {
        this.list = list;
        this.featuredBlogList = list2;
        this.activity = activity;
        this.mix = z;
        this.orList = list3;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new HorizontalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.features_blog_item_with_recycler, viewGroup, false));
        }
        return new VerticalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.blogs_item_layout, viewGroup, false));
    }

    @Override 
    public int getItemViewType(int i) {
        if (this.mix) {
            return (i == 50 || i == 0 || i == this.list.size() - 1) ? 1 : 0;
        }
        return 0;
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {



        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType == 0) {
            VerticalViewHolder verticalViewHolder = (VerticalViewHolder) viewHolder;
            verticalViewHolder.blogCoverImg.setClipToOutline(true);
            verticalViewHolder.blogCoverImg.setImageResource(Utils.setImage(this.list.get(i).getImgPath()));

            verticalViewHolder.blogTitleTv.setText(this.list.get(i).getHeading());
            String trim = this.list.get(i).getBody().trim();
            try {
                if (trim.startsWith("<h1>") && !trim.endsWith("</h1>")) {
                    trim = trim.split("</h1>")[1].trim();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            verticalViewHolder.blogDescTv.setText(Utils.htmlToText(trim));
            verticalViewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    GeneralBlogAdapter.this.m128x70e5f4b8(i, view);
                }
            });
            setAnimation(verticalViewHolder.itemView, i);
        } else if (itemViewType != 1) {
        } else {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) viewHolder;
            Collections.shuffle(this.featuredBlogList);
            if (new MyThemeHandler().getAppTheme(this.activity).isDark()) {
                horizontalViewHolder.forYouTv.setTextColor(this.activity.getResources().getColor(R.color.white));
            }
            horizontalViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(this.activity, RecyclerView.HORIZONTAL, false));
            horizontalViewHolder.recyclerView.setAdapter(new FeaturedBlogAdapter(this.featuredBlogList, this.activity));

            horizontalViewHolder.blogCoverImg.setImageResource(Utils.setImage(this.list.get(i).getImgPath()));
            horizontalViewHolder.blogTitleTv.setText(this.list.get(i).getHeading());
            String body = this.list.get(i).getBody();
            horizontalViewHolder.bottomBlogArea.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    GeneralBlogAdapter.this.m129x700c8417(i, view);
                }
            });
            if (body != null) {
                if (body.startsWith("<h1>")) {
                    try {
                        body = body.split("</h1>")[1];
                    } catch (IndexOutOfBoundsException e2) {
                        e2.printStackTrace();
                    }
                }
                horizontalViewHolder.blogDescTv.setText(Utils.htmlToText(body));
            }
            if (i == this.list.size() - 1) {
                reverseLinearLayout((LinearLayout) horizontalViewHolder.itemView);
                horizontalViewHolder.forYouTv.setVisibility(View.GONE);
            }
        }
    }

    
    
    public  void m128x70e5f4b8(int i, View view) {
        Intent intent = new Intent(this.activity, ReadBlogActivity.class);
        intent.putExtra("heading", this.list.get(i).getHeading());
        intent.putExtra("imgRes", this.list.get(i).getImgPath());
        intent.putExtra("body", this.list.get(i).getBody());
        intent.putExtra("color", this.list.get(i).getColor());
        intent.putExtra("dark", this.list.get(i).isDark());
        this.activity.startActivity(intent);
    }

    
    
    public  void m129x700c8417(int i, View view) {
        Intent intent = new Intent(this.activity, ReadBlogActivity.class);
        intent.putExtra("heading", this.list.get(i).getHeading());
        intent.putExtra("imgRes", this.list.get(i).getImgPath());
        intent.putExtra("body", this.list.get(i).getBody());
        intent.putExtra("color", this.list.get(i).getColor());
        intent.putExtra("dark", this.list.get(i).isDark());
        this.activity.startActivity(intent);
    }

    private void setAnimation(View view, int i) {
        if (i > this.lastPosition) {
            view.startAnimation(AnimationUtils.loadAnimation(this.activity, R.anim.top_slider_animation));
            this.lastPosition = i;
        }
    }

    @Override 
    public int getItemCount() {
        return this.list.size();
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    
    public class HorizontalViewHolder extends ViewHolder {
        ImageView blogCoverImg;
        TextView blogDescTv;
        TextView blogTitleTv;
        ConstraintLayout bottomBlogArea;
        TextView forYouTv;
        RecyclerView recyclerView;

        public HorizontalViewHolder(View view) {
            super(view);
            this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            this.forYouTv = (TextView) view.findViewById(R.id.forYouTv);
            this.blogCoverImg = (ImageView) view.findViewById(R.id.blogCoverImg);
            this.blogTitleTv = (TextView) view.findViewById(R.id.blogTitleTv);
            this.blogDescTv = (TextView) view.findViewById(R.id.blogDescTv);
            this.bottomBlogArea = (ConstraintLayout) view.findViewById(R.id.bottomBlogArea);
        }
    }

    
    public class VerticalViewHolder extends ViewHolder {
        ImageView blogCoverImg;
        TextView blogDescTv;
        TextView blogTitleTv;

        public VerticalViewHolder(View view) {
            super(view);
            this.blogCoverImg = (ImageView) view.findViewById(R.id.blogCoverImg);
            this.blogTitleTv = (TextView) view.findViewById(R.id.blogTitleTv);
            this.blogDescTv = (TextView) view.findViewById(R.id.blogDescTv);
        }
    }

    private void reverseLinearLayout(LinearLayout linearLayout) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            arrayList.add(linearLayout.getChildAt(i));
        }
        linearLayout.removeAllViews();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            linearLayout.addView((View) arrayList.get(size));
        }
    }
}
