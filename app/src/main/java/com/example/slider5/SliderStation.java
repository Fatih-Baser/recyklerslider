package com.example.slider5;



import java.util.Arrays;
import java.util.List;



public class SliderStation {


    public static SliderStation get() {
        return new SliderStation();
    }

    private SliderStation() {
    }

    public List<Slider> getForecasts() {

        // json cekme ve bitmape donusturme
        // decode bilmem ne
        // return Arrays.asList(
        //  for dongusu icinde olsturdugun listenin icine atacaksin new Slider ( "cityname", decode);
        // return slider listesi

        return Arrays.asList(
                new Slider("Pisa", R.drawable.kucuk1),
                new Slider("Paris", R.drawable.daire1),
                new Slider("New York", R.drawable.seffaf),
                new Slider("Rome", R.drawable.yuvarlak),
                new Slider("London", R.drawable.yuvarlak),
                new Slider("Washinn", R.drawable.yuvarlak));
    }
}
