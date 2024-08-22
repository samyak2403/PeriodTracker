package com.arrowwould.periodtracker.Model;

import java.util.List;


public class BlogCategory {
    List<CategoryFeaturedBlog> blogList;
    String name;
    int nameRes;

    public BlogCategory(String str, int i, List<CategoryFeaturedBlog> list) {
        this.name = str;
        this.blogList = list;
        this.nameRes = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<CategoryFeaturedBlog> getBlogList() {
        return this.blogList;
    }

    public void setBlogList(List<CategoryFeaturedBlog> list) {
        this.blogList = list;
    }

    public int getNameRes() {
        return this.nameRes;
    }

    public void setNameRes(int i) {
        this.nameRes = i;
    }
}
