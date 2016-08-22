package com.example.jamesrondina.syncadapterslab;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jamesrondina on 8/22/16.
 */
public interface StockInterface {
    @GET("json")
    Call<Stock> getStockDetails(@Query("symbol") String symbol);


}
