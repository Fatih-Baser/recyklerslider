package com.example.Slider.sample.Slider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.example.Slider.sample.R;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SliderActivity extends AppCompatActivity implements
        DiscreteScrollView.ScrollStateChangeListener<SliderAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<SliderAdapter.ViewHolder>,
        View.OnClickListener {

    private List<Slider> sliders;
    Button start;
    ProgressBar progressBar;
    private int counter=0;
    Timer t=new Timer();
    TimerTask tt;

    RelativeLayout progress;

    CardView cardwiew;
    private DiscreteScrollView cityPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        progressBarkismi();


        sliders = SliderStation.get().getForecasts();
        cityPicker = findViewById(R.id.forecast_city_picker);
        cityPicker.setSlideOnFling(true);
        cityPicker.setAdapter(new SliderAdapter(sliders));
        cityPicker.addOnItemChangedListener(this);
        cityPicker.addScrollStateChangeListener(this);
        cityPicker.scrollToPosition(0);
        cityPicker.setItemTransitionTimeMillis(SliderScrollViewOptions.getTransitionTime());
        cityPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.9f)
                .build());


//        View container = cardwiew.findViewById(R.id.inside_layout);
//        container.setBackgroundColor(col);
  }

    @Override
    public void onCurrentItemChanged(@Nullable SliderAdapter.ViewHolder holder, int position) {

        if (holder != null) {

            holder.showText();
        }
    }

    @Override
    public void onScrollStart(@NonNull SliderAdapter.ViewHolder holder, int position) {
        holder.hideText();
    }

    @Override
    public void onScroll(
            float position,
            int currentIndex, int newIndex,
            @Nullable SliderAdapter.ViewHolder currentHolder,
            @Nullable SliderAdapter.ViewHolder newHolder) {
        Slider current = sliders.get(currentIndex);
        RecyclerView.Adapter<?> adapter = cityPicker.getAdapter();
        int itemCount = adapter != null ? adapter.getItemCount() : 0;

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onScrollEnd(@NonNull SliderAdapter.ViewHolder holder, int position) {

    }
    @SuppressLint("ClickableViewAccessibility")
    public void progressBarkismi(){
        start=findViewById(R.id.button);
        progressBar=findViewById(R.id.progress_bar);

        progress=findViewById(R.id.progressLayout);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager(). getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        System.out.println("WIDTH == " + width);
        progress.setHorizontalGravity(width/2);
        progress.setVerticalGravity(height/2);

        start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, final MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        t=new Timer();
                        tt=new TimerTask() {
                            @Override
                            public void run() {
                                counter++;
                                progressBar.setProgress(counter);
                                if(counter ==100)
                                    t.cancel();

                            }

                        };
                        t.schedule(tt,0,100);
                        progressBar.setVisibility(View.VISIBLE);
                        return true;
                    case MotionEvent.ACTION_UP:

                        t.cancel();
                        progressBar.setProgress(counter=0);

                        return true;
                }
                return false;
            }
        });
}}
