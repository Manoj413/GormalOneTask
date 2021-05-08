package com.gormal.gormalonetask.core.networking;

import com.gormal.gormalonetask.product.model.Product;
import com.gormal.gormalonetask.productList.model.Books;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("addNewProduct")
    Observable<Product> pushProductData(@Body RequestBody requestBody);

   /* @FormUrlEncoded
    @POST("addNewProduct")
    Observable<Product> pushProductData2(@FieldMap Map<String, Object> map );
*/
    @GET("getAllAvailableBooks")
    Observable<Books> getBooksData();

}
