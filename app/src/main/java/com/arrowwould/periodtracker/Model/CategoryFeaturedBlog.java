package com.arrowwould.periodtracker.Model;


public class CategoryFeaturedBlog extends Blog {
    String detail;

    public CategoryFeaturedBlog(String str, String str2, String i, String str3, String str4, boolean z) {
        super(str, str2, i, str4, z);
        this.detail = str3;
    }

    public CategoryFeaturedBlog(String str, String i, String str2, boolean z) {
        super(str, i);
        this.detail = str2;
        this.isDark = z;
    }

    public CategoryFeaturedBlog(String str, String i, boolean z) {
        super(str, i);
        this.isDark = z;
    }

    public CategoryFeaturedBlog() {
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }
}
