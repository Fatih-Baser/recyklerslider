package com.example.Slider.sample.Slider;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.ArrayRes;


public class SliderView extends LinearLayout {

    private Paint gradientPaint;
    private int[] currentGradient;



    private ArgbEvaluator evaluator;

    public SliderView(Context context) {
        super(context);
    }

    public SliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        evaluator = new ArgbEvaluator();

        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setWillNotDraw(false);

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

    }

    private void initGradient() {
        float centerX = getWidth() * 0.5f;
        Shader gradient = new LinearGradient(
                centerX, 0, centerX, getHeight(),
                currentGradient, null,
                Shader.TileMode.MIRROR);
        gradientPaint.setShader(gradient);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (currentGradient != null) {
            initGradient();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), gradientPaint);
        super.onDraw(canvas);
    }



    private int[] colors(@ArrayRes int res) {
        return getContext().getResources().getIntArray(res);
    }

}
