package com.arrowwould.periodtracker.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.arrowwould.periodtracker.Databases.Entities.DateDetails;
import com.arrowwould.periodtracker.Databases.OvulationDetailsHandler;
import com.arrowwould.periodtracker.Databases.Params;
import com.arrowwould.periodtracker.R;
import com.arrowwould.periodtracker.ThemesFiles.MyCustomTheme;
import com.arrowwould.periodtracker.ThemesFiles.MyThemeHandler;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

import java.text.ParseException;
import java.util.List;


public class MyCustomMonthView extends MonthView {
    private final int circleRadius;
    private final Context context;
    private final int margin;
    private final float strokeWidth;

    public MyCustomMonthView(Context context) {
        super(context);
        this.margin = 10;
        this.circleRadius = 40;
        this.strokeWidth = 5.0f;
        this.context = context;
    }

    @Override 
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int i, int i2, boolean z) {
        int i3 = i + (this.mItemWidth / 2);
        float f = (float) (i2 + (this.mItemHeight / 2.5d));
        MyCustomTheme appTheme = new MyThemeHandler().getAppTheme((Activity) this.context);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(this.context.getResources().getColor(appTheme.getThemeColor()));
        paint.setStrokeWidth(5.0f);
        canvas.drawCircle(i3, f, 40.0f, paint);
        return true;
    }

    @Override 
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int i, int i2) {
        int i3 = i + (this.mItemWidth / 2);
        float f = (float) (i2 + (this.mItemHeight / 2.5d));
        Paint paint = new Paint();
        List<DateDetails> allOvulationDetails = new OvulationDetailsHandler(this.context).getAllOvulationDetails(Params.OVULATION_DETAILS_TABLE_CALENDAR);
        String valueOf = String.valueOf(calendar.getDay());
        String valueOf2 = String.valueOf(calendar.getMonth());
        String valueOf3 = String.valueOf(calendar.getYear());
        if (calendar.getDay() < 10) {
            valueOf = "0" + valueOf;
        }
        if (calendar.getMonth() < 10) {
            valueOf2 = "0" + valueOf2;
        }
        String str = valueOf3 + "-" + valueOf2 + "-" + valueOf;
        int i4 = 25;
        Bitmap bitmap = null;
        for (DateDetails dateDetails : allOvulationDetails) {
            try {
                if (dateDetails.getNextPeriod().equals(str)) {
                    bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_next_period_indicator);
                    paint.setColor(this.context.getResources().getColor(R.color.safe_days_cal_color));
                    canvas.drawCircle(i3, f, 40.0f, paint);
                } else if (MyDateUtils.checkDate(str, OvulationCalculations.minusDays(dateDetails.getFertileDays().split("---")[0].trim(), 1), OvulationCalculations.addDays(dateDetails.getFertileDays().split("---")[1].trim(), 1), "yyyy-MM-dd")) {
                    if (dateDetails.getOvulationPeriod().equals(str)) {
                        paint.setColor(this.context.getResources().getColor(R.color.fertile_days_cal_color));
                        paint.setStrokeWidth(5.0f);
                        paint.setStyle(Paint.Style.FILL);
                        paint.setAntiAlias(true);
                        canvas.drawCircle(i3, f, 40.0f, paint);
                        bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_next_ovulation_indicator);
                        i4 = 30;
                    } else {
                        paint.setColor(this.context.getResources().getColor(R.color.fertile_days_cal_color));
                        canvas.drawCircle(i3, f, 40.0f, paint);
                    }
                } else if (MyDateUtils.checkDate(str, dateDetails.getNextPeriod(), OvulationCalculations.addDays(dateDetails.getNextPeriod(), Integer.parseInt(SharedPreferenceUtils.getCycleLength((Activity) this.context))), "yyyy-MM-dd")) {
                    paint.setColor(this.context.getResources().getColor(R.color.safe_days_cal_color));
                    canvas.drawCircle(i3, f, 40.0f, paint);
                }
            } catch (ParseException e) {
                Log.d("TAG", "error --> " + e.getMessage());
            }
        }
        paint.setColor(this.context.getResources().getColor(R.color.half_transparent_color));
        canvas.drawCircle(i3, f, 40.0f, paint);
        if (bitmap != null) {
            try {
                canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, i4, 30, true), ((i + this.mItemWidth) - 10) - 20, i2, (Paint) null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override 
    protected void onDrawText(Canvas canvas, Calendar calendar, int i, int i2, boolean z, boolean z2) {
        Paint paint = new Paint();
        float f = this.mTextBaseLine + i2;
        int i3 = i + (this.mItemWidth / 2);
        float f2 = (float) (i2 + (this.mItemHeight / 2.5d));
        if (!z) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.context.getResources().getColor(R.color.half_transparent_color));
            canvas.drawCircle(i3, f2, 40.0f, paint);
        }
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        paint2.setTextSize(25.0f);
        canvas.drawText(String.valueOf(calendar.getDay()), i3 - (paint2.measureText(String.valueOf(calendar.getDay())) / 2.0f), f - 10.0f, paint2);
    }
}
