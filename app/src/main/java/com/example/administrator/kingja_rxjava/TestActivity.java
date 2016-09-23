package com.example.administrator.kingja_rxjava;

import android.graphics.Bitmap;
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
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Description：TODO
 * Create Time：2016/9/22 13:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TestActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        onRxClick();

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error!");
            }
        };
/**
 * 创建观察者
 */
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.e(TAG, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error!");
            }
        };
/**
 * 创建被观察者
 */
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        /**
         * 被观察者添加观察者，这样被观察者发生变化就会第一时间通知观察者
         */
//        observable.subscribe(subscriber);

//        String[] names = {"A", "B", "C"};
//        Observable.from(names)
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String name) {
//                        Log.d(TAG, name);
//                    }
//                });
//
//
//        Integer[] items = {0, 1, 2, 3, 4, 5};
//        Observable myObservable = Observable.from(items);
//        Observable.just("images/logo.png") // 输入类型 String
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String filePath) {
//                        return null;// 参数类型 String
//                    }
//                })
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
//                    }
//                });

//        Person[] arr = {new Person("TOM", 15), new Person("JACK", 18), new Person("KING", 20)};
//        Observable.from(arr).flatMap(new Func1<Person, Observable<String>>() {
//            @Override
//            public Observable<String> call(Person person) {
//                return Observable.just(person.getName());
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e(TAG, "call: "+s  );
//            }
//        });

    }

    private void onRxClick() {
        btn = (Button) findViewById(R.id.btn);
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
