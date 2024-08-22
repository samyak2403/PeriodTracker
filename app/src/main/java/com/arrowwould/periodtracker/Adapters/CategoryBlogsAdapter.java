package com.arrowwould.periodtracker.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Model.BlogCategory;
import com.arrowwould.periodtracker.Model.CategoryFeaturedBlog;
import com.arrowwould.periodtracker.R;
import java.util.Collections;
import java.util.List;


public class CategoryBlogsAdapter extends RecyclerView.Adapter<CategoryBlogsAdapter.ViewHolder> {
    private final Activity activity;
    private final List<BlogCategory> blogCategories;

    public CategoryBlogsAdapter(List<BlogCategory> list, Activity activity) {
        this.blogCategories = list;
        this.activity = activity;
    }

    @Override 
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_blog_item, viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.categoryNameTv.setText(this.blogCategories.get(i).getNameRes());
        List<CategoryFeaturedBlog> blogList = this.blogCategories.get(i).getBlogList();
        Collections.shuffle(blogList);
        viewHolder.categoryBlogsRecyclerView.setLayoutManager(new LinearLayoutManager(this.activity, RecyclerView.HORIZONTAL, false));
        viewHolder.categoryBlogsRecyclerView.setAdapter(new CategoryFeaturedBlogAdapter(blogList, this.activity));
    }

    @Override 
    public int getItemCount() {
        return this.blogCategories.size();
    }

    
    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView categoryBlogsRecyclerView;
        TextView categoryNameTv;

        public ViewHolder(View view) {
            super(view);
            this.categoryNameTv = (TextView) view.findViewById(R.id.categoryNameTv);
            this.categoryBlogsRecyclerView = (RecyclerView) view.findViewById(R.id.categoryBlogsRecycler);
        }
    }
}
