package com.example.liuxiaoming.upwork;

import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by LJW on 2018/4/22.
 */

public class UpGroup {

    private List<UpWorker> mWorkers;

    public Iterable<? extends ObservableSource<?>> getWork1() {

        return null;
    }

    public Function<? super Object,? extends ObservableSource<?>> getWork2() {
        return null;
    }
}
