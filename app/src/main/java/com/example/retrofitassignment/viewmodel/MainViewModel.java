package com.example.retrofitassignment.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitassignment.network.RetrofitAPI;
import com.example.retrofitassignment.network.data.model.MarsProperty;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private final RetrofitAPI retrofitAPI = RetrofitAPI.getInstance();
    public final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>(false);
    private final MutableLiveData<List<MarsProperty>> mMarsPropertyList = new MutableLiveData<>();
    public final MutableLiveData<Boolean> mError = new MutableLiveData<>(false);

    public LiveData<List<MarsProperty>> getMarsPropertyList() {
        return mMarsPropertyList;
    }


    private final MutableLiveData<MarsProperty> mCurrentMarsProperty = new MutableLiveData<>();
    public LiveData<MarsProperty> getCurrentMarsProperty() {
        return mCurrentMarsProperty;
    }
    public void setCurrentMarsProperty(MarsProperty marsProperty) {
        mCurrentMarsProperty.setValue(marsProperty);
    }



    public void loadMarsPropertyList() {
        mIsLoading.setValue(true);
        retrofitAPI.mRetrofitAPIService.getProperties().enqueue(new Callback<List<MarsProperty>>() {
            @Override
            public void onResponse(@NonNull Call<List<MarsProperty>> call, @NonNull Response<List<MarsProperty>> response) {
                List<MarsProperty> marsPropertyList = response.body();
                mMarsPropertyList.setValue(marsPropertyList);
                mError.setValue(false);
                mIsLoading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<MarsProperty>> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                mError.setValue(true);
                mIsLoading.setValue(false);
            }
        });
    }

    public void loadMarsPropertyList(String filter) {
        mIsLoading.setValue(true);
        retrofitAPI.mRetrofitAPIService.getPropertiesByType(filter).enqueue(new Callback<List<MarsProperty>>() {
            @Override
            public void onResponse(@NonNull Call<List<MarsProperty>> call, @NonNull Response<List<MarsProperty>> response) {
                List<MarsProperty> marsPropertyList = response.body();
                mMarsPropertyList.setValue(marsPropertyList);
                mError.setValue(false);
                mIsLoading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<MarsProperty>> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                mError.setValue(true);
                mIsLoading.setValue(false);
            }
        });
    }

}
