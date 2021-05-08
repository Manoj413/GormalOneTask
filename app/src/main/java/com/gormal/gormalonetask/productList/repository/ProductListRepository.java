package com.gormal.gormalonetask.productList.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gormal.gormalonetask.core.networking.ApiInterface;
import com.gormal.gormalonetask.productList.model.Books;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductListRepository {
    private static final String TAG = ProductListRepository.class.getSimpleName();
    ApiInterface apiInterface;
    Application mApplication;
    private final MutableLiveData<Boolean> mLoadingState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mErrorState = new MutableLiveData<>();
    @Inject
    public ProductListRepository(Application application, ApiInterface apiInterface) {
        this.apiInterface=apiInterface;
        this.mApplication =application;
    }

    public MutableLiveData<Books> getBooksData() {
        Log.e("BookDataApi","BookDataApi");
        MutableLiveData<Books> booksMutableLiveData = new MutableLiveData<>();
        mLoadingState.setValue(true);
        Observable<Books> call = apiInterface.getBooksData();

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Books>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("oNSub_push1","oNSub_push1 = ");
                    }

                    @Override
                    public void onNext(Books value) {

                        Log.e("onNext_push2","onNext_push2 = "+value);
                        if (value!=null){
                            booksMutableLiveData.setValue(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError_push3","onError_push3 = "+e);
                        booksMutableLiveData.setValue(null);
                        mLoadingState.setValue(false);
                        mErrorState.setValue(true);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete_push4","onComplete_push4 = ");
                        mLoadingState.setValue(false);
                        mErrorState.setValue(false);
                    }
                });
        return booksMutableLiveData;
    }


    public MutableLiveData<Boolean> getLoadingStateFromBooksRepo(){
        return mLoadingState;
    }
    public MutableLiveData<Boolean> getErrorStateFromBooksRepo(){
        return mErrorState;
    }
}

