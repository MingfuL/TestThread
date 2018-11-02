package com.example.liuxiaoming.upwork;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpChain {

    private List<UpWorker> mUpWorkers;
    private UpDirector mUpDirector;

    public UpChain(@NotNull UpDirector upDirector, @NotNull List<UpWorker> upWorkers) {
        this.mUpDirector = upDirector;
        this.mUpWorkers = upWorkers;
    }


    public UpChain then(UpWorker... upWorkers) {
        List<UpWorker> upWorkersList = new ArrayList<>(Arrays.asList(upWorkers));
        this.mUpWorkers.addAll(upWorkersList);
        return this;
    }

    public UpResult action() {
        return this.mUpDirector.action(mUpWorkers);
    }
}
