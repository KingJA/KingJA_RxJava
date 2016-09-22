package com.example.administrator.kingja_rxjava;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;

/**
 * Description：TODO
 * Create Time：2016/9/22 13:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TestActivity extends AppCompatActivity {
    private String TAG=getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button btn = (Button) findViewById(R.id.btn);
        RxView.clicks(btn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.e(TAG, "onNext: ");
                    }
                });
    }

}
