package com.gormal.gormalonetask.product.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gormal.gormalonetask.core.networking.ApiInterface;
import com.gormal.gormalonetask.product.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class ProductRepository {
    private static final String TAG = ProductRepository.class.getSimpleName();
    ApiInterface apiInterface;
    private final MutableLiveData<Boolean> mLoadingState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mErrorState = new MutableLiveData<>();
    @Inject
    public ProductRepository(Application application, ApiInterface apiInterface) {
        this.apiInterface=apiInterface;
    }

    public MutableLiveData<Boolean> pushProductData(ArrayList<Product> allData) {
        MutableLiveData<Boolean> userLoginMutableLiveData = new MutableLiveData<>();
        mLoadingState.setValue(true);


        JSONArray jsonArray = new JSONArray();

        for (Product product : allData){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("product_name",product.getProduct_name());
                jsonObject.put("product_desc",product.getProduct_desc());
                jsonObject.put("product_quantity",product.getProduct_quantity());
                jsonObject.put("product_price",product.getProduct_price());
                jsonObject.put("user_mobile_no",product.getUser_mobile_no());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.e("JSONARR","JSONARR = "+jsonArray);
        RequestBody body =  RequestBody.create(okhttp3.MediaType.parse("application/json"), jsonArray.toString());

       /* Map<String,Object> map = new HashMap<>();
        for (int i=0;i<allData.size();i++) {
            Product user= allData.get(i);
            map.put("product_name"+i,user.getProduct_name());
            map.put("product_desc"+i,user.getProduct_desc());
            map.put("product_quantity"+i,user.getProduct_quantity());
            map.put("product_price"+i,user.getProduct_price());
            map.put("user_mobile_no"+i,user.getUser_mobile_no());
        }*/

        Observable<Product> call = apiInterface.pushProductData(body);

        call.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Product>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.e("oNSub_push","oNSub_push = ");
                }

                @Override
                public void onNext(Product value) {

                    Log.e("onNext_push","onNext_push = "+value.toString());
                    if (value!=null){
                        userLoginMutableLiveData.setValue(true);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("onError_push","onError_push = "+e);
                    userLoginMutableLiveData.setValue(false);
                    mLoadingState.setValue(false);
                    mErrorState.setValue(true);
                }

                @Override
                public void onComplete() {
                    Log.e("onComplete_push","onComplete_push = ");
                    mLoadingState.setValue(false);
                    mErrorState.setValue(false);
                }
            });



        return userLoginMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoadingStateFromProductRepo(){
        return mLoadingState;
    }
    public MutableLiveData<Boolean> getErrorStateFromProductRepo(){
        return mErrorState;
    }
}
