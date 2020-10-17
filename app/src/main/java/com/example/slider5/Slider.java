package com.example.slider5;

public class Slider {

    private final String cityName;
    private final int cityIcon;


    public Slider(String cityName, int cityIcon) {
        this.cityName = cityName;
        this.cityIcon = cityIcon;

    }

    public String getCityName() {
        return cityName;
    }

    public int getCityIcon() {
        return cityIcon;
    }


}
