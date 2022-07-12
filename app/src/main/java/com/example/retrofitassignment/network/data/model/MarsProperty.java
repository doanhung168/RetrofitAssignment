package com.example.retrofitassignment.network.data.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.squareup.moshi.Json;

import java.util.Objects;

public class MarsProperty {

    private @Json(name = "id")
    String mId;

    private @Json(name = "type")
    String mType;

    private @Json(name = "img_src")
    String mImgSrc;

    private @Json(name = "price")
    double mPrice;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getImgSrc() {
        return mImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.mImgSrc = imgSrc;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(Double price) {
        this.mPrice = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarsProperty that = (MarsProperty) o;
        return Double.compare(that.mPrice, mPrice) == 0 && Objects.equals(mId, that.mId) && Objects.equals(mType, that.mType) && Objects.equals(mImgSrc, that.mImgSrc);
    }

    public static DiffUtil.ItemCallback<MarsProperty> diffCallback = new DiffUtil.ItemCallback<MarsProperty>() {
        @Override
        public boolean areItemsTheSame(@NonNull MarsProperty oldItem, @NonNull MarsProperty newItem) {
            return oldItem.mId.equals(newItem.mId);
        }

        @Override
        public boolean areContentsTheSame(@NonNull MarsProperty oldItem, @NonNull MarsProperty newItem) {
            return oldItem.equals(newItem);
        }
    };
}
