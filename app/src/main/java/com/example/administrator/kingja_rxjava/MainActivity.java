package com.example.administrator.kingja_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
    private static final String URL_GET="http://gank.io/api/history/content/2/";

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        loadByRetrofit();

        }

    private void loadByRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_GET)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GanHuoService apiService = retrofit.create(GanHuoService.class);


        Call<GanHuoApi> call = apiService.loadNet(1);

        call.enqueue(new Callback<GanHuoApi>() {
            @Override
            public void onResponse(Call<GanHuoApi> call, Response<GanHuoApi> response) {
                GanHuoApi body = response.body();
                Log.i(TAG, "onResponse: "+ body.getResults().get(0).getContent());
            }

            @Override
            public void onFailure(Call<GanHuoApi> call, Throwable t) {
                Log.i(TAG, "onFailure: "+ t.toString());
            }
        });
    }
}
