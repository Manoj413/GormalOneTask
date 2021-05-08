package com.gormal.gormalonetask.productDetails.repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.gormal.gormalonetask.core.networking.ApiInterface;

import javax.inject.Inject;

public class AddProductRespository {
    private static final String TAG = AddProductRespository.class.getSimpleName();
    ApiInterface mApiInterface;
    Application mApplication;
    private final MutableLiveData<Boolean> mLoadingState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mErrorState = new MutableLiveData<>();
    @Inject
    public AddProductRespository(Application application, ApiInterface apiInterface) {
        this.mApiInterface=apiInterface;
        this.mApplication =application;
    }


    public MutableLiveData<Boolean> getLoadingStateFromRepo(){
        return mLoadingState;
    }
    public MutableLiveData<Boolean> getErrorStateFromRepo(){
        return mErrorState;
    }


    public void pushToLocalDB(String picturePath, Uri imageUri, String name){

    }

}

