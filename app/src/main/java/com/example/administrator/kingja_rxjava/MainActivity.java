package com.example.administrator.kingja_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.kingja_rxjava.entiy.HotShowing;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
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
                .baseUrl(Constants.DOUBAN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        DouBanService apiService = retrofit.create(DouBanService.class);
        doNetByRetrofit(apiService);
        doNetByRxJava(apiService);

    }

    private void doNetByRxJava(DouBanService apiService) {
        apiService.getHotShowing()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<HotShowing>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: " );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.toString());
                    }

                    @Override
                    public void onNext(HotShowing hotShowing) {
                        Log.e(TAG, "HotShowing: " + hotShowing.getTitle());
                    }
                });
    }

    private void doNetByRetrofit(DouBanService apiService) {
        Call<HotShowing> call = apiService.getHotShowingByR();
        call.enqueue(new Callback<HotShowing>() {
            @Override
            public void onResponse(Call<HotShowing> call, Response<HotShowing> response) {
                Log.e(TAG, "Retrofit: " + response.body().getTitle());
            }

            @Override
            public void onFailure(Call<HotShowing> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
