package com.example.Slider.sample.Slider;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.Slider.sample.R;

import java.util.List;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private RecyclerView parentRecycler;
    private List<Slider> data;

    public SliderAdapter(List<Slider> data) {
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parentRecycler = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_city_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int iconTint = ContextCompat.getColor(holder.itemView.getContext(), R.color.grayIconTint);
        Slider slider = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(slider.getCityIcon())
                .listener(new TintOnLoad(holder.imageView, iconTint))
                .into(holder.imageView);
        holder.textView.setText(slider.getCityName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.city_image);
            textView = itemView.findViewById(R.id.city_name);

            itemView.findViewById(R.id.container).setOnClickListener(this);
        }

        public void showText() {
            int parentHeight = ((View) imageView.getParent()).getHeight();
            float scale = (80+parentHeight - textView.getHeight()) / (float) imageView.getHeight();
            imageView.setPivotX(imageView.getWidth() * 0.52f);
            imageView.setPivotY(imageView.getWidth() * 0.52f);
            imageView.animate().scaleX(scale)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            textView.setVisibility(View.VISIBLE);
                            imageView.setColorFilter(Color.BLACK);
                        }
                    })
                    .scaleY(scale).setDuration(200)
                    .start();
        }

        public void hideText() {
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.grayIconTint));
            textView.setVisibility(View.INVISIBLE);
            imageView.animate().scaleX(1f).scaleY(1f)
                    .setDuration(200)
                    .start();
        }

        @Override
        public void onClick(View v) {
            parentRecycler.smoothScrollToPosition(getAdapterPosition());
        }
    }

    private static class TintOnLoad implements RequestListener<Drawable> {

        private ImageView imageView;
        private int tintColor;

        public TintOnLoad(ImageView view, int tintColor) {
            this.imageView = view;
            this.tintColor = tintColor;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
            imageView.setColorFilter(tintColor);
            return false;
        }

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
            return false;
        }
    }
}
