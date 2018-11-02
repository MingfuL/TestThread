package com.example.liuxiaoming.upwork;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class UpResult {

    public static final String RX_OBSERVABLE_KEY = "RX";

    public static final int UPRESULT_NONE = 0; // 没有返回值
    public static final int UPRESULT_DATA = 1; // 有返回值
    public static final int UPRESULT_OBSERABLE = 2; // 有返回值

    private UpData mData;
    private Observable observable;
    private int resultType = 0;

    public UpResult(int type) {
        if (type == UPRESULT_DATA) {
            throw new IllegalArgumentException("type can not be UPRESULT_DATA without UpData");
        }
        this.resultType = type;
    }

    public UpResult(int type, UpData mData) {
        this.resultType = type;
        this.mData = mData;
    }

    public UpResult(int resultType, Observable observable) {
        this.observable = observable;
        this.resultType = resultType;
    }


    public <T> void subscribe(Observer<T> observer) {

        if (resultType == UPRESULT_DATA) {
            // UpData
            Object value = mData.getKeyValueMap().get(RX_OBSERVABLE_KEY);
            if (value == null) {
                throw new IllegalArgumentException("rx observable is null");
            }

            if (!(value instanceof Observable)) {
                throw new IllegalArgumentException("value of " + RX_OBSERVABLE_KEY + " is not Observable");
            }

            ((Observable<T>) value).subscribe(observer);

        } else if (resultType == UPRESULT_OBSERABLE) {
            // Observable
            if (observable == null) {
                throw new IllegalArgumentException("observable is null");
            }

            observable.subscribe(observer);
        }
    }


    public UpData getmData() {
        return mData;
    }

    public int getResultType() {
        return resultType;
    }

}
