package com.example.retrofitassignment.util;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter(value = {"imageUrl", "error", "loading"})
    public static void loadImage(ImageView view, String url, Drawable error, Drawable loading) {

        Uri uri = Uri.parse(url).buildUpon().scheme("https").build();
        Picasso.with(view.getContext())
                .load(uri)
                .placeholder(loading)
                .error(error)
                .into(view);
    }

    @androidx.databinding.BindingAdapter({"currencyText"})
    public static void currencyText(TextView textView, double number) {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        currency.setMaximumFractionDigits(0);
        String myCurrency = currency.format(number);
        textView.setText(myCurrency);
    }

}
