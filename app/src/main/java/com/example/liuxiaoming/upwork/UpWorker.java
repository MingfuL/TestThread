package com.example.liuxiaoming.upwork;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by LJW on 2018/4/18.
 */

public abstract class UpWorker<B extends UpWorker.Builder> {


    public UpData inputData;
    public UpData outputData;
    public Observer callback;
    boolean backgroundThread;

    public UpWorker(Builder<B> builder) {
        this.inputData = builder.inputData;
        this.backgroundThread = builder.backgroundThread;
    }

    public enum Result {
        SUCCESS,
        FAILURE,
        RETRY
    }

    public UpData getInputData() {
        return inputData;
    }

    public void setInputData(UpData inputData) {
        this.inputData = inputData;
    }

    public UpData getOutputData() {
        return outputData;
    }

    public void setOutputData(UpData outputData) {
        this.outputData = outputData;
    }

//    public abstract Result doWork();
    public abstract Observable doWork();

    public static abstract class Builder<B extends Builder> {
        public UpData inputData;
        public boolean backgroundThread = false;

        public abstract Object build();

        public abstract B getThis();

        public B setInputData(UpData res1) {
            this.inputData = res1;
            return getThis();
        }

        public B backgroundThread(boolean backgroundThread) {
            this.backgroundThread = backgroundThread;
            return getThis();
        }
    }

}
