package com.chrisaj.chocotest.model;

import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chrisaj.chocotest.R;
import com.chrisaj.chocotest.tool.TimeTool;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import androidx.databinding.BindingAdapter;

public class DramaModel {

    /**
     * drama_id : 3
     * name : 如果有妹妹就好了
     * total_views : 2931180
     * created_at : 2017-10-21T12:34:41.000Z
     * thumb : https://i.pinimg.com/originals/32/c1/7a/32c17af1c085be75657e965954f8d601.jpg
     * rating : 4.0647
     */

    private int drama_id;
    private String name;
    private int total_views;
    private String created_at;
    private String thumb;
    private float rating;

    public int getDrama_id() {
        return drama_id;
    }

    public void setDrama_id(int drama_id) {
        this.drama_id = drama_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_views() {
        return total_views;
    }

    public void setTotal_views(int total_views) {
        this.total_views = total_views;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    // BindingAdapter for item image loading
    @BindingAdapter({"itemImage"})
    public static void loadItemImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .override(720,420)
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .into(imageView);
    }

    // 調整顯示時間格式
    @BindingAdapter({ "timeFormat" })
    public static void transferTimeFormat(TextView textView, String time) {
        textView.setText(TimeTool.TransformTimeFormat(time));
    }
    // 調整RatingBar
    @BindingAdapter({ "setSize" })
    public static void setRatingBarSize(SimpleRatingBar ratingBar, float rating) {
        float original =  (rating * 100) / 5 ;
        float result   = original/100;
        ratingBar.setRating(0);
    }
    // 調整RatingBar textView
    @BindingAdapter({ "setRating" })
    public static void setRatingCount(TextView textView, float rating) {
        String original = String.valueOf(rating);

        textView.setText(String.valueOf(Float.valueOf(original.substring(0,4))));
    }
}
