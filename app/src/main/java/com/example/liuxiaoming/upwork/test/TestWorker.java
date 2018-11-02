package com.example.liuxiaoming.upwork.test;

import android.util.Log;

import com.example.liuxiaoming.testthread.MainActivity;
import com.example.liuxiaoming.upwork.UpData;
import com.example.liuxiaoming.upwork.UpWorker;
import com.example.liuxiaoming.upwork.Utils;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

import static com.example.liuxiaoming.testthread.MainActivity.COUNT;

public class TestWorker extends UpWorker<TestWorker.Builder> {

    private String tag = "TestWorker";

    public TestWorker(TestWorker.Builder builder) {
        super(builder);
    }

    @Override
    public Observable doWork() {

        final String count = (String) inputData.getKeyValueMap().get(COUNT);

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(tag,"编号："+count+" 发送爱心～～"+" thread-->"+Utils.getThreadSignature()+" 时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));
                e.onNext("编号："+count+" 发送爱心～～"+" thread-->"+Utils.getThreadSignature()+" 时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));
            }
        }).doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Utils.sleepForInSecs(2);
                        Log.d(tag,"编号："+count+" 爱心传送中。。。"+" thread-->"+Utils.getThreadSignature()+" 时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));

                        UpData upData = new UpData.Builder()
                                .putString(COUNT, count)
                                .build();

                        setOutputData(inputData);
                    }
                });

        return observable;

    }

    public static final class Builder extends UpWorker.Builder<Builder> {

        @Override
        public TestWorker build() {
            return new TestWorker(this);
        }

        @Override
        public TestWorker.Builder getThis() {
            return this;
        }
    }
}
