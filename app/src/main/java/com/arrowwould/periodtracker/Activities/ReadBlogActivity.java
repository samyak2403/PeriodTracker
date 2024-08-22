package com.arrowwould.periodtracker.Activities;



import static com.arrowwould.periodtracker.Utils.Utils.setImage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arrowwould.periodtracker.Adapters.FeaturedBlogAdapter;
import com.arrowwould.periodtracker.Adapters.InnerArticlesAdapter;
import com.arrowwould.periodtracker.Databases.Entities.Likes;
import com.arrowwould.periodtracker.Databases.Entities.Recents;
import com.arrowwould.periodtracker.Databases.LikesHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.Databases.RecentsHandler;
import com.arrowwould.periodtracker.Model.Blog;
import com.arrowwould.periodtracker.Model.FeaturedBlog;
import com.arrowwould.periodtracker.Utils.ImageUtils;
import com.arrowwould.periodtracker.Utils.Utils;
import com.arrowwould.periodtracker.databinding.ActivityReadBlogBinding;
;
import com.google.android.material.appbar.AppBarLayout;
import com.arrowwould.periodtracker.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class ReadBlogActivity extends AppCompatActivity {
    ActivityReadBlogBinding binding;
    private String heading;
    private boolean isCategory;
    private LikesHandler likesHandler;
    private RecentsHandler recentsHandler;
    private String title;

    public static  void $r8$lambda$aXsKotI_KZxDpm_Cb4W_ttMxORw(ReadBlogActivity readBlogActivity) {
        readBlogActivity.loadGeneralArticles();
    }

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityReadBlogBinding inflate = ActivityReadBlogBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());

//        AdsGoogle adsGoogle = new AdsGoogle( this);
//        adsGoogle.Banner_Show((RelativeLayout) findViewById(R.id.banner), this);
//        adsGoogle.Interstitial_Show_Counter(this);


        Utils.setFullScreen(this);
        this.binding.backButton.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                ReadBlogActivity.this.m119x79587603(view);
            }
        });
        this.likesHandler = new LikesHandler(this);
        this.recentsHandler = new RecentsHandler(this);
        Intent intent = getIntent();
        this.heading = intent.getStringExtra("heading");
        this.title = intent.getStringExtra("title");
        String stringExtra = intent.getStringExtra("color");
        this.isCategory = intent.getBooleanExtra("categories", false);
        if (intent.getBooleanExtra("dark", false)) {
            ImageUtils.setTint(this.binding.likeButton, R.color.white);
        }
        this.binding.blogHeadingTv.setText(this.heading);
        this.binding.blogBodyTv.setText(Html.fromHtml(intent.getStringExtra("body")));

        this.binding.blogImg.setImageResource(setImage(intent.getStringExtra("imgRes")));

        checkLike();
        handleRecent();
        if (!this.isCategory) {
            this.binding.fab.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    ReadBlogActivity.this.m120x787f0562(view);
                }
            });
            this.binding.likeButton.setOnClickListener(new View.OnClickListener() { 
                @Override 
                public final void onClick(View view) {
                    ReadBlogActivity.this.m121x77a594c1(view);
                }
            });
            this.binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { 
                boolean isShow = true;
                int scrollRange = -1;



                @Override 
                public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                    if (this.scrollRange == -1) {
                        this.scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (this.scrollRange + i == 0) {
                        ReadBlogActivity.this.binding.collapsingToolbar.setTitle(" ");
                        ReadBlogActivity.this.binding.contentArea.setBackgroundResource(R.color.white);
                        this.isShow = true;
                        ReadBlogActivity.this.binding.likeButton.setVisibility(View.VISIBLE);
                    } else if (this.isShow) {
                        ReadBlogActivity.this.binding.contentArea.setBackgroundResource(R.drawable.up_rounded_bg);
                        ReadBlogActivity.this.binding.collapsingToolbar.setTitle(" ");
                        this.isShow = false;
                        ReadBlogActivity.this.binding.likeButton.setVisibility(View.GONE);
                    }
                    Utils.setFullScreen(ReadBlogActivity.this);
                }
            });
        } else {
            this.binding.fab.setImageResource(R.drawable.ic_blog);
            this.binding.likeButton.setVisibility(View.GONE);
        }
        this.binding.parentLayout.setBackgroundColor(Color.parseColor(stringExtra));
        if (this.title != null && !this.isCategory) {
            loadHorizontalArticles();
        } else {
            new Handler().postDelayed(new Runnable() { 
                @Override 
                public final void run() {
                    ReadBlogActivity.$r8$lambda$aXsKotI_KZxDpm_Cb4W_ttMxORw(ReadBlogActivity.this);
                }
            }, 300L);
        }
    }

    
    public  void m119x79587603(View view) {
        finish();
    }

    
    public  void m120x787f0562(View view) {
        handleLike();
    }

    
    public  void m121x77a594c1(View view) {
        handleLike();
    }

    private void loadHorizontalArticles() {
        ArrayList arrayList = new ArrayList();
        List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(this, Locale.getDefault().getLanguage() + ".json");
        List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(this, "en.json");
        if (readAssetFile != null && readAssetFile2 != null) {
            for (int i = 0; i < readAssetFile.size(); i++) {
                HashMap<String, Object> hashMap = readAssetFile.get(i);
                String str = this.title;
                if (str != null && !str.equals(hashMap.get("title").toString())) {
                    arrayList.add(new FeaturedBlog(hashMap.get("heading").toString(), hashMap.get("body").toString(), Utils.lowerUnder(readAssetFile2.get(i).get("title").toString()), hashMap.get("title").toString(), readAssetFile2.get(i).get("color").toString(), ((Boolean) readAssetFile2.get(i).get("dark")).booleanValue()));
                }
            }
            readAssetFile.clear();
        }
        Collections.shuffle(arrayList);
        List subList = arrayList.subList(0, 7);
        this.binding.featuredBlogsRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        this.binding.featuredBlogsRecycler.setAdapter(new FeaturedBlogAdapter(subList, this));
        showData();
    }

    public void loadGeneralArticles() {
        ArrayList arrayList = new ArrayList();
        List<HashMap<String, Object>> readAssetFile = Utils.readAssetFile(this, Locale.getDefault().getLanguage() + "_g.json");
        List<HashMap<String, Object>> readAssetFile2 = Utils.readAssetFile(this, "en_g.json");
        if (readAssetFile != null && readAssetFile2 != null) {
            for (int i = 0; i < readAssetFile.size() && i < readAssetFile2.size(); i++) {
                HashMap<String, Object> hashMap = readAssetFile.get(i);
                if (!this.heading.equals(hashMap.get("heading").toString())) {
                    arrayList.add(new Blog(hashMap.get("heading").toString(), hashMap.get("body").toString(), Utils.lowerUnder(readAssetFile2.get(i).get("heading").toString()), readAssetFile2.get(i).get("color").toString(), ((Boolean) readAssetFile2.get(i).get("dark")).booleanValue()));
                }
            }
            readAssetFile.clear();
        }
        Collections.shuffle(arrayList);
        List subList = arrayList.subList(0, 5);
        this.binding.generalBlogsRecycler.setLayoutManager(new LinearLayoutManager(this));
        this.binding.generalBlogsRecycler.setAdapter(new InnerArticlesAdapter(subList, this));
        showData();
    }

    private void showData() {
        this.binding.topOptions.setVisibility(View.VISIBLE);
        this.binding.fab.setVisibility(View.VISIBLE);
        this.binding.blogImg.setVisibility(View.VISIBLE);
        this.binding.contentArea.setVisibility(View.VISIBLE);
        this.binding.pb.setVisibility(View.GONE);
    }

    private void handleLike() {
        String str = this.title;
        if (str != null) {
            Likes likeByParam = this.likesHandler.getLikeByParam(str, Params.KEY_LIKES_TITLE);
            if (likeByParam == null) {
                Likes likes = new Likes();
                likes.setTitle(this.title);
                this.likesHandler.addLike(likes);
                this.binding.fab.setImageResource(R.drawable.ic_liked);
                this.binding.likeButton.setImageResource(R.drawable.ic_liked);
                return;
            }
            this.likesHandler.deleteLike(String.valueOf(likeByParam.getId()));
            this.binding.fab.setImageResource(R.drawable.ic_like);
            this.binding.likeButton.setImageResource(R.drawable.ic_like);
            return;
        }
        Likes likeByParam2 = this.likesHandler.getLikeByParam(this.heading, Params.KEY_LIKES_HEADING);
        if (likeByParam2 == null) {
            Likes likes2 = new Likes();
            likes2.setHeading(this.heading);
            this.likesHandler.addLike(likes2);
            this.binding.fab.setImageResource(R.drawable.ic_liked);
            this.binding.likeButton.setImageResource(R.drawable.ic_liked);
            return;
        }
        this.likesHandler.deleteLike(String.valueOf(likeByParam2.getId()));
        this.binding.fab.setImageResource(R.drawable.ic_like);
        this.binding.likeButton.setImageResource(R.drawable.ic_like);
    }

    private void handleRecent() {
        String str = this.title;
        if (str != null) {
            Recents recentByParam = this.recentsHandler.getRecentByParam(str, Params.KEY_RECENTS_TITLE);
            if (recentByParam != null) {
                this.recentsHandler.deleteRecent(String.valueOf(recentByParam.getId()));
            }
            Recents recents = new Recents();
            recents.setTitle(this.title);
            this.recentsHandler.addRecent(recents);
            return;
        }
        Recents recentByParam2 = this.recentsHandler.getRecentByParam(this.heading, Params.KEY_RECENTS_HEADING);
        if (recentByParam2 != null) {
            this.recentsHandler.deleteRecent(String.valueOf(recentByParam2.getId()));
        }
        Recents recents2 = new Recents();
        recents2.setHeading(this.heading);
        this.recentsHandler.addRecent(recents2);
    }

    private void checkLike() {
        String str = this.title;
        if (str != null) {
            if (this.likesHandler.getLikeByParam(str, Params.KEY_LIKES_TITLE) != null) {
                this.binding.fab.setImageResource(R.drawable.ic_liked);
                this.binding.likeButton.setImageResource(R.drawable.ic_liked);
                return;
            }
            this.binding.fab.setImageResource(R.drawable.ic_like);
            this.binding.likeButton.setImageResource(R.drawable.ic_like);
        } else if (this.likesHandler.getLikeByParam(this.heading, Params.KEY_LIKES_HEADING) != null) {
            this.binding.fab.setImageResource(R.drawable.ic_liked);
            this.binding.likeButton.setImageResource(R.drawable.ic_liked);
        } else {
            this.binding.likeButton.setImageResource(R.drawable.ic_like);
            this.binding.fab.setImageResource(R.drawable.ic_like);
        }
    }
}
