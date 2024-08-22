package com.arrowwould.periodtracker.ThemesFiles;


public class MyCustomTheme {
    private final int bgColor;
    private final int bgImg;
    private final boolean isDark;
    private final int themeColor;

    public MyCustomTheme(int i, int i2, int i3, boolean z) {
        this.bgImg = i;
        this.themeColor = i2;
        this.isDark = z;
        this.bgColor = i3;
    }

    public int getBgImg() {
        return this.bgImg;
    }

    public int getThemeColor() {
        return this.themeColor;
    }

    public boolean isDark() {
        return this.isDark;
    }

    public int getBgColor() {
        return this.bgColor;
    }
}
