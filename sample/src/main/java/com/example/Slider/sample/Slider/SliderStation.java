package com.example.Slider.sample.Slider;

import com.example.Slider.sample.R;

import java.util.Arrays;
import java.util.List;



public class SliderStation {


    public static SliderStation get() {
        return new SliderStation();
    }

    private SliderStation() {
    }

    public List<Slider> getForecasts() {
        return Arrays.asList(
                new Slider("Pisa", R.drawable.yuvarlak),
                new Slider("Paris", R.drawable.yuvarlak),
                new Slider("New York", R.drawable.seffaf),
                new Slider("Rome", R.drawable.yuvarlak),
                new Slider("London", R.drawable.yuvarlak),
                new Slider("Washinn", R.drawable.yuvarlak));
    }
}
