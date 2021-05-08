package com.gormal.gormalonetask.productDetails.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gormal.gormalonetask.core.networking.RemoteDatabase;
import com.gormal.gormalonetask.productDetails.repository.AddProductRespository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
@HiltViewModel
public class AddProductViewModel extends ViewModel {
    AddProductRespository addProductRespository;
    Application application;
    RemoteDatabase remoteDatabase;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public AddProductViewModel(Application application, AddProductRespository addProductRespository, RemoteDatabase remoteDatabase) {
        this.application = application;
        this.addProductRespository = addProductRespository;
        this.remoteDatabase = remoteDatabase;
    }

    public AddProductViewModel() {

    }

    private void fetchLoaderState() {
        loading = addProductRespository.getLoadingStateFromRepo();
    }


    private void fetchLoadError() {
        repoLoadError = addProductRespository.getErrorStateFromRepo();
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }
    public LiveData<Boolean> getLoading() {
        fetchLoaderState();
        return loading;
    }

   /* public LiveData<List<ModerationLabel>> getModerationLabelData() {
        moderationLabelMutableLiveData=profileRepository.getModerationLabelDataFromRepo();
        return moderationLabelMutableLiveData;
    }*/
    public boolean uploadToLocalDatabase(String name, String description, String quantity, String price, String phone_no){
        boolean inserted = remoteDatabase.insertProductData( name,  description,  quantity,  price, phone_no);
        return inserted;
    }
}


