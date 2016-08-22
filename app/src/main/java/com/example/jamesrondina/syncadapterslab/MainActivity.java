package com.example.jamesrondina.syncadapterslab;

import android.content.ContentProviderClient;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Cursor cursor;
    SimpleCursorAdapter cursorAdapter;
    ListView mListView;
    private static final String API_URL = "http://dev.markitondemand.com/MODApis/Api/v2/Quote/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri myURI = Uri.parse("content://drewmahrt.generalassemb.ly.investingportfolio.MyContentProvider/stocks");
        ContentProviderClient client = getContentResolver().acquireContentProviderClient(myURI);

/*
        //Make API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();

        StockInterface service = retrofit.create(StockInterface.class);
        Call<Stock> stockCall = service.getStockDetails("VZ");
        stockCall.enqueue(new Callback<Stock>() {
            @Override
            public void onResponse(Call<Stock> call, Response<Stock> response) {

                Log.d("API Call", response.body().getName());
                Log.d("API Call", response.body().getLastPrice().toString());
                Log.d("API Call", response.body().getTimestamp());


            }

            @Override
            public void onFailure(Call<Stock> call, Throwable t) {

            }
        });
*/

        try {
            cursor = client.query(myURI,
                    null,
                    null,
                    null,
                    null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        mListView = (ListView) findViewById(R.id.listView);

        cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{MyDBHandler.COLUMN_STOCKNAME, MyDBHandler.COLUMN_PRICE},
                new int[]{android.R.id.text1,android.R.id.text2},0);

        mListView.setAdapter(cursorAdapter);



    }
}
