package com.gormal.gormalonetask.product.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gormal.gormalonetask.core.networking.RemoteDatabase;
import com.gormal.gormalonetask.product.repository.ProductRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
@HiltViewModel
public class ProductViewModel extends ViewModel {
    ProductRepository productRepository;
    Application application;
    RemoteDatabase remoteDatabase;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Boolean> productLiveData = new MutableLiveData<>();

    @Inject
    public ProductViewModel(Application application, ProductRepository productRepository, RemoteDatabase remoteDatabase) {
        this.application = application;
        this.productRepository = productRepository;
        this.remoteDatabase = remoteDatabase;
    }
    private void fetchLoaderState() {
        loading = productRepository.getLoadingStateFromProductRepo();
    }


    private void fetchLoadError() {
        repoLoadError = productRepository.getErrorStateFromProductRepo();
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }
    public LiveData<Boolean> getLoading() {
        fetchLoaderState();
        return loading;
    }

    public LiveData<Boolean> uploadProdcutInfoToServer(){
        Log.e("Synch_data","Synch_data = "+remoteDatabase.getAllData());
        productLiveData = productRepository.pushProductData(remoteDatabase.getAllData());
        return productLiveData;
    }
}


