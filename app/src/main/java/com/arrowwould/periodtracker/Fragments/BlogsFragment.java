package com.arrowwould.periodtracker.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Adapters.FeaturedBlogAdapter;
import com.arrowwould.periodtracker.Adapters.GeneralBlogAdapter;
import com.arrowwould.periodtracker.Databases.Entities.Likes;
import com.arrowwould.periodtracker.Databases.Entities.Recents;
import com.arrowwould.periodtracker.Databases.LikesHandler;
import com.arrowwould.periodtracker.Databases.RecentsHandler;
import com.arrowwould.periodtracker.Model.Blog;
import com.arrowwould.periodtracker.Model.FeaturedBlog;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.arrowwould.periodtracker.Utils.ImageUtils;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.FragmentBlogsBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class BlogsFragment extends Fragment {
    private static final String TAG = "BlogsTAG";
    FragmentBlogsBinding binding;
    private int themeColor;

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = FragmentBlogsBinding.inflate(layoutInflater);
        showDiscoverData();
        setUpTheme();
        this.binding.discoverBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                BlogsFragment.this.m131x5204738e(view);
            }
        });
        this.binding.savedBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                BlogsFragment.this.m132xb45f8a6d(view);
            }
        });
        this.binding.recentBtn.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                BlogsFragment.this.m133x16baa14c(view);
            }
        });
        return this.binding.getRoot();
    }

    
    
    public  void m131x5204738e(View view) {
        activateBtn(this.binding.discoverBtn);
        deActivateBtn(this.binding.savedBtn);
        deActivateBtn(this.binding.recentBtn);
        showDiscoverData();
    }

    
    
    public  void m132xb45f8a6d(View view) {
        deActivateBtn(this.binding.discoverBtn);
        activateBtn(this.binding.savedBtn);
        deActivateBtn(this.binding.recentBtn);
        showSavedData();
    }

    
    
    public  void m133x16baa14c(View view) {
        deActivateBtn(this.binding.discoverBtn);
        deActivateBtn(this.binding.savedBtn);
        activateBtn(this.binding.recentBtn);
        showRecentData();
    }

    private void activateBtn(LinearLayout linearLayout) {
        ImageUtils.setTint((ImageView) linearLayout.getChildAt(0), R.color.white);
        ((TextView) linearLayout.getChildAt(1)).setTextColor(getResources().getColor(R.color.white));
        Utils.setTint(linearLayout, this.themeColor);
    }

    private void deActivateBtn(LinearLayout linearLayout) {
        ImageUtils.setTint((ImageView) linearLayout.getChildAt(0), this.themeColor);
        ((TextView) linearLayout.getChildAt(1)).setTextColor(getResources().getColor(this.themeColor));
        Utils.setTint(linearLayout, R.color.white);
    }

    private void setUpTheme() {
        this.themeColor = new MyThemeHandler().getAppTheme(getActivity()).getThemeColor();
        activateBtn(this.binding.discoverBtn);
        deActivateBtn(this.binding.savedBtn);
        deActivateBtn(this.binding.recentBtn);
    }

    private void showDiscoverData() {
        this.binding.discoverRecycler.setVisibility(View.VISIBLE);
        this.binding.othersContentArea.setVisibility(View.GONE);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Context context = getContext();
        List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(context, Locale.getDefault().getLanguage() + ".json");
        List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(getContext(), "en.json");
        if (readAssetFile != null && readAssetFile2 != null) {
            for (int i = 0; i < readAssetFile.size() && i < readAssetFile2.size(); i++) {
                HashMap<String, Object> hashMap = readAssetFile.get(i);
                arrayList3.add(readAssetFile2.get(i).get("title").toString());
                arrayList2.add(new FeaturedBlog(hashMap.get("heading").toString(), hashMap.get("body").toString(), Utils.lowerUnder(readAssetFile2.get(i).get("title").toString()), hashMap.get("title").toString(), readAssetFile2.get(i).get("color").toString(), ((Boolean) readAssetFile2.get(i).get("dark")).booleanValue()));
            }
            readAssetFile.clear();
        }
        Context context2 = getContext();
        List<HashMap<String, Object>> readAssetFile3 = Utils.readAssetFile(context2, Locale.getDefault().getLanguage() + "_g.json");
        List<HashMap<String, Object>> readAssetFile4 = Utils.readAssetFile(getContext(), "en_g.json");
        if (readAssetFile3 != null && readAssetFile4 != null) {
            int i2 = 0;
            while (i2 < readAssetFile3.size()) {
                Log.d("BlogsTAG", "name --> " + Utils.lowerUnder(readAssetFile4.get(i2).get("heading").toString()));
                HashMap<String, Object> hashMap2 = readAssetFile3.get(i2);
                arrayList.add(new Blog(hashMap2.get("heading").toString(), hashMap2.get("body").toString(), Utils.lowerUnder(readAssetFile4.get(i2).get("heading").toString()), readAssetFile4.get(i2).get("color").toString(), ((Boolean) readAssetFile4.get(i2).get("dark")).booleanValue()));
                i2++;
                readAssetFile3 = readAssetFile3;
            }
        }
        Collections.shuffle(arrayList);
        this.binding.discoverRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.discoverRecycler.setAdapter(new GeneralBlogAdapter(arrayList, arrayList2, arrayList3, getActivity(), true));
    }

    private void showRecentData() {
        this.binding.discoverRecycler.setVisibility(View.GONE);
        this.binding.othersContentArea.setVisibility(View.VISIBLE);
        ArrayList<Blog> arrayList = new ArrayList();
        ArrayList<FeaturedBlog> arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Context context = getContext();
        List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(context, Locale.getDefault().getLanguage() + ".json");
        List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(getContext(), "en.json");
        if (readAssetFile != null && readAssetFile2 != null) {
            for (int i = 0; i < readAssetFile.size(); i++) {
                HashMap<String, Object> hashMap = readAssetFile.get(i);
                arrayList3.add(readAssetFile2.get(i).get("title").toString());
                try {
                    arrayList2.add(new FeaturedBlog(hashMap.get("heading").toString(), hashMap.get("body").toString(),Utils.lowerUnder(readAssetFile2.get(i).get("title").toString()), hashMap.get("title").toString(), readAssetFile2.get(i).get("color").toString(), ((Boolean) readAssetFile2.get(i).get("dark")).booleanValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            readAssetFile.clear();
        }
        Context context2 = getContext();
        List<HashMap<String, Object>> readAssetFile3 = Utils.readAssetFile(context2, Locale.getDefault().getLanguage() + "_g.json");
        List<HashMap<String, Object>> readAssetFile4 = Utils.readAssetFile(getContext(), "en_g.json");
        if (readAssetFile3 != null && readAssetFile4 != null) {
            int i2 = 0;
            while (i2 < readAssetFile3.size()) {
                HashMap<String, Object> hashMap2 = readAssetFile3.get(i2);
                arrayList.add(new Blog(hashMap2.get("heading").toString(), hashMap2.get("body").toString(), Utils.lowerUnder(readAssetFile4.get(i2).get("heading").toString()), readAssetFile4.get(i2).get("color").toString(), ((Boolean) readAssetFile4.get(i2).get("dark")).booleanValue()));
                i2++;
                readAssetFile3 = readAssetFile3;
            }
        }
        List<Recents> allRecents = new RecentsHandler(getActivity()).getAllRecents();
        ArrayList<Blog> arrayList4 = new ArrayList<Blog>();
        ArrayList<FeaturedBlog> arrayList5 = new ArrayList<FeaturedBlog>();
        for (Recents recents : allRecents) {
            if (recents.getTitle() != null) {
                for (FeaturedBlog featuredBlog : arrayList2) {
                    if (featuredBlog.getDetail().equals(recents.getTitle())) {
                        arrayList5.add(featuredBlog);
                    }
                }
            } else {
                for (Blog blog : arrayList) {
                    if (blog.getHeading().equals(recents.getHeading())) {
                        arrayList4.add(blog);
                    }
                }
            }
        }
        Collections.reverse(arrayList4);
        Collections.reverse(arrayList5);
        this.binding.verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.binding.verticalRecyclerView.setAdapter(new GeneralBlogAdapter(arrayList4, arrayList5, arrayList3, getActivity(), false));
        this.binding.horizontalRecyclerView.setAdapter(new FeaturedBlogAdapter(arrayList5, getActivity()));
    }

    private void showSavedData() {
        this.binding.discoverRecycler.setVisibility(View.GONE);
        this.binding.othersContentArea.setVisibility(View.VISIBLE);
        ArrayList<Blog> arrayList = new ArrayList();
        ArrayList<FeaturedBlog> arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Context context = getContext();
        List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(context, Locale.getDefault().getLanguage() + ".json");
        List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(getContext(), "en.json");
        if (readAssetFile != null && readAssetFile2 != null) {
            for (int i = 0; i < readAssetFile.size(); i++) {
                HashMap<String, Object> hashMap = readAssetFile.get(i);
                arrayList3.add(readAssetFile2.get(i).get("title").toString());
                arrayList2.add(new FeaturedBlog(hashMap.get("heading").toString(), hashMap.get("body").toString(), Utils.lowerUnder(readAssetFile2.get(i).get("title").toString()), hashMap.get("title").toString(), readAssetFile2.get(i).get("color").toString(), ((Boolean) readAssetFile2.get(i).get("dark")).booleanValue()));
            }
            readAssetFile.clear();
        }
        Context context2 = getContext();
        List<HashMap<String, Object>> readAssetFile3 = Utils.readAssetFile(context2, Locale.getDefault().getLanguage() + "_g.json");
        List<HashMap<String, Object>> readAssetFile4 = Utils.readAssetFile(getContext(), "en_g.json");
        if (readAssetFile3 != null && readAssetFile4 != null) {
            int i2 = 0;
            while (i2 < readAssetFile3.size()) {
                HashMap<String, Object> hashMap2 = readAssetFile3.get(i2);
                arrayList.add(new Blog(hashMap2.get("heading").toString(), hashMap2.get("body").toString(), Utils.lowerUnder(readAssetFile4.get(i2).get("heading").toString()), readAssetFile4.get(i2).get("color").toString(), ((Boolean) readAssetFile4.get(i2).get("dark")).booleanValue()));
                i2++;
                readAssetFile3 = readAssetFile3;
            }
        }
        List<Likes> allLikes = new LikesHandler(getActivity()).getAllLikes();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (Likes likes : allLikes) {
            if (likes.getTitle() != null) {
                for (FeaturedBlog featuredBlog : arrayList2) {
                    if (featuredBlog.getDetail().equals(likes.getTitle())) {
                        arrayList5.add(featuredBlog);
                    }
                }
            } else {
                for (Blog blog : arrayList) {
                    if (blog.getHeading().equals(likes.getHeading())) {
                        arrayList4.add(blog);
                    }
                }
            }
        }
        Collections.reverse(arrayList4);
        Collections.reverse(arrayList5);
        this.binding.verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        this.binding.verticalRecyclerView.setAdapter(new GeneralBlogAdapter(arrayList4, arrayList5, arrayList3, getActivity(), false));
        this.binding.horizontalRecyclerView.setAdapter(new FeaturedBlogAdapter(arrayList5, getActivity()));
    }
}
