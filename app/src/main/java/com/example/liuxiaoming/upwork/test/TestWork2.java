package com.example.liuxiaoming.upwork.test;

import android.text.TextUtils;
import android.util.Log;

import com.example.liuxiaoming.testthread.MainActivity;
import com.example.liuxiaoming.upwork.UpWorker;
import com.example.liuxiaoming.upwork.Utils;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class TestWork2  extends UpWorker<TestWork2.Builder> {

    private String tag = "TestWork2";

    public TestWork2(TestWork2.Builder builder) {
        super(builder);
    }

    @Override
    public Observable doWork() {

        final String count = (String) inputData.getKeyValueMap().get(MainActivity.COUNT);

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(tag,"编号："+count+" 发送大便～～"+" thread-->"+Utils.getThreadSignature()+" 时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));
                e.onNext( "编号："+count+" 发送大便～～"+" thread-->"+Utils.getThreadSignature()+" 时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Utils.sleepForInSecs(2);
                Log.d(tag,"编号："+count+" 大便传送中。。。"+" thread-->"+Utils.getThreadSignature()+" 时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));
            }
        });

        return observable;

    }

    public static final class Builder extends UpWorker.Builder<Builder> {

        @Override
        public TestWork2 build() {
            return new TestWork2(this);
        }

        @Override
        public TestWork2.Builder getThis() {
            return this;
        }
    }
}
