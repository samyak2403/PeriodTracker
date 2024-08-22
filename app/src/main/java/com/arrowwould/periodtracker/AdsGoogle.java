//package com.arrowwould.periodtracker;
//
//
//import android.app.Activity;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.util.Log;
//import android.view.View;
//import android.widget.RelativeLayout;
////
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//
//
//public class AdsGoogle {
//
//    public  static String AD_Banner_ID = "/6499/example/banner";
//    public  static String AD_Interstitial_ID = "/6499/example/interstitial";
//
//
//    static ProgressDialog ProgressDialog;
//    public AdsGoogle(Activity activity) {
//
//        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus
//                                                         initializationStatus) {
//            }
//        });
//
//
//    }
//
//    public void Interstitial_Show(final Activity activity) {
//        Ad_Popup(activity);
//
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(activity, AD_Interstitial_ID, adRequest,
//                new InterstitialAdLoadCallback() {
//                    @Override
//                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//
//                        interstitialAd.show(activity);
//                        ProgressDialog.dismiss();
//
//                    }
//
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//
//                        ProgressDialog.dismiss();
//                    }
//                });
//    }
//
//
//    private static final String Counter_Ads = "Counter_Ads";
//
//
//    public static void setCounter_Ads(Context mContext, int string) {
//        mContext.getSharedPreferences(mContext.getPackageName(), 0).edit()
//                .putInt(Counter_Ads, string).commit();
//    }
//
//    public static int getCounter_Ads(Context mContext) {
//        return mContext.getSharedPreferences(mContext.getPackageName(), 0)
//                .getInt(Counter_Ads, 1);
//    }
//
//    public static void Interstitial_Show_Counter(final Activity activity) {
//
//
//
//        int counter_ads = getCounter_Ads(activity);
//
//        if (counter_ads >= 3) {
//
//            setCounter_Ads(activity, 1);
//
//            try {
//
//                Ad_Popup(activity);
//
//                AdRequest adRequest = new AdRequest.Builder().build();
//
//                InterstitialAd.load(activity, AD_Interstitial_ID, adRequest,
//                        new InterstitialAdLoadCallback() {
//                            @Override
//                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//
//                                interstitialAd.show(activity);
//                                ProgressDialog.dismiss();
//
//                            }
//
//                            @Override
//                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//
//                                ProgressDialog.dismiss();
//                            }
//                        });
//
//            } catch (Exception e) {
//
//            }
//
//        } else {
//            counter_ads = counter_ads + 1;
//            setCounter_Ads(activity, counter_ads);
//
//        }
//
//    }
//
//
//
//
//     private static void Ad_Popup(Context mContext) {
//
//        ProgressDialog = ProgressDialog.show(mContext, "Please Wait...", "Loading Ads", true);
//        ProgressDialog.setCancelable(true);
//        ProgressDialog.show();
//
//    }
//
//    public void Banner_Show(final RelativeLayout Ad_Layout, Activity activity) {
//
//
//        AdView mAdView = new AdView(activity);
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId(AD_Banner_ID);
//        AdRequest adore = new AdRequest.Builder().build();
//        mAdView.loadAd(adore);
//        Ad_Layout.addView(mAdView);
//
//
//        mAdView.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdLoaded() {
//                Ad_Layout.setVisibility(View.VISIBLE);
//                super.onAdLoaded();
//
//                Log.e("ddddd", "dddd");
//            }
//
//            @Override
//            public void onAdOpened() {
//                super.onAdOpened();
//                Ad_Layout.setVisibility(View.INVISIBLE);
//                Log.e("ddddd1", "dddd");
//
//            }
//
//            @Override
//            public void onAdFailedToLoad(LoadAdError loadAdError) {
//                super.onAdFailedToLoad(loadAdError);
//                mAdView.destroy();
//                Ad_Layout.setVisibility(View.INVISIBLE);
//                Log.e("ddddd2", "dddd" + loadAdError.getMessage());
//
//            }
//        });
//
//
//    }
//
//
//
//
//}