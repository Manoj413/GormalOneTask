package com.gormal.gormalonetask.productList.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gormal.gormalonetask.core.networking.RemoteDatabase;
import com.gormal.gormalonetask.productList.model.Books;
import com.gormal.gormalonetask.productList.repository.ProductListRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListViewModel extends ViewModel {
    ProductListRepository productListRepository;
    Application application;
    RemoteDatabase remoteDatabase;
    private MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Books> booksLiveData = new MutableLiveData<>();

    @Inject
    public ProductListViewModel(Application application, ProductListRepository productListRepository, RemoteDatabase remoteDatabase) {
        this.application = application;
        this.productListRepository = productListRepository;
        this.remoteDatabase = remoteDatabase;
        Log.e("getBookData1","getBookData1");
    }

    public ProductListViewModel() {

    }

    private void fetchLoaderState() {
        loading = productListRepository.getLoadingStateFromBooksRepo();
    }


    private void fetchLoadError() {
        repoLoadError = productListRepository.getErrorStateFromBooksRepo();
    }

    LiveData<Boolean> getError() {
        return repoLoadError;
    }
    public LiveData<Boolean> getLoading() {
        fetchLoaderState();
        return loading;
    }
        public LiveData<Books> getBooksData() {
        Log.e("getBookData2","getBookData2");
            booksLiveData=productListRepository.getBooksData();
            return booksLiveData;
        }

}


