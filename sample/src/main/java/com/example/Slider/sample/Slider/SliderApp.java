package com.example.Slider.sample.Slider;

import android.app.Application;


public class SliderApp extends Application {

    private static SliderApp instance;

    public static SliderApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SliderScrollViewOptions.init(this);
    }
}
