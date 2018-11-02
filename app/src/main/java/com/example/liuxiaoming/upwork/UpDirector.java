package com.example.liuxiaoming.upwork;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LJW on 2018/4/18.
 */

public class UpDirector {


    private static UpDirector instance;

    public static UpDirector getInstance() {
        if (instance == null) {
            return new UpDirector();
        }

        return instance;
    }

//    public UpResult action(final List<UpWorker> upWorkerList) {
//
//        if (upWorkerList == null || upWorkerList.size() == 0) {
//            return new UpResult(UpResult.UPRESULT_NONE);
//        }
//
//        if (upWorkerList.get(0).doWork() != UpWorker.Result.SUCCESS) {
//            // handle error callback
//        }
//
//        if (upWorkerList.size() == 1) {
//            return new UpResult(UpResult.UPRESULT_DATA, upWorkerList.get(0).getOutputData());
//        }
//        // 遍历执行列表中的doWork, 并将UpData向后传递
//        for (int i = 1; i < upWorkerList.size(); i++) {
//            upWorkerList.get(i).setInputData(upWorkerList.get(i - 1).getOutputData());
//
//            if (upWorkerList.get(i).doWork() != UpWorker.Result.SUCCESS) {
//                // handle error callback
//                break;
//            }
//        }
//
//        // 返回最后一个worker的outputData
//        return new UpResult(UpResult.UPRESULT_DATA, upWorkerList.get(upWorkerList.size() - 1).getOutputData());
//    }

    public UpResult action(final List<UpWorker> upWorkerList) {
        if (upWorkerList == null || upWorkerList.size() == 0) {
            return new UpResult(UpResult.UPRESULT_NONE);
        }

        Observable observable = upWorkerList.get(0).doWork()
//                .subscribeOn(upWorkerList.get(0).backgroundThread ? Schedulers.from(ThreadManager.getUploadsericePool()) : Schedulers.trampoline());
                .subscribeOn(upWorkerList.get(0).backgroundThread ? Schedulers.from(ThreadManager.getUploadsericePool()) : Schedulers.trampoline());

        if (upWorkerList.size() == 1) {
            return new UpResult(UpResult.UPRESULT_OBSERABLE, observable);
        }
        // 遍历执行列表中的doWork, 并将UpData向后传递
        for (int i = 1; i < upWorkerList.size(); i++) {

            final int finalI = i;
            observable = observable
                    .observeOn(upWorkerList.get(finalI).backgroundThread ? Schedulers.from(ThreadManager.getUploadsericePool()) : Schedulers.trampoline())
                    .flatMap(new Function() {
                        @Override
                        public Object apply(Object o) throws Exception {
                            UpData beforeOutputData = upWorkerList.get(finalI - 1).getOutputData();
                            UpData thisInputData = upWorkerList.get(finalI).getInputData();
                            if (thisInputData != null) {

                                Map<String, Object> keyValueMap = beforeOutputData.getKeyValueMap();
                                Map<String, Object> keyValueMap1 = thisInputData.getKeyValueMap();

                                if (beforeOutputData != null) {
                                    UpData.Builder builder = new UpData.Builder();

                                    for (Map.Entry<String, Object> entry : keyValueMap.entrySet()) {
                                        String key = entry.getKey();
                                        Object value = entry.getValue();
                                        builder.putObject(key, value);
                                    }

                                    for (Map.Entry<String, Object> entry : keyValueMap1.entrySet()) {
                                        String key = entry.getKey();
                                        Object value = entry.getValue();
                                        builder.putObject(key, value);
                                    }

                                    UpData newInputData = builder.build();
                                    upWorkerList.get(finalI).setInputData(newInputData);
                                }
                            } else {
                                upWorkerList.get(finalI).setInputData(upWorkerList.get(finalI - 1).getOutputData());
                            }

                            return upWorkerList.get(finalI).doWork();
                        }
                    });

        }

        return new UpResult(UpResult.UPRESULT_OBSERABLE, observable);
    }

//    public void action(UpWorker upWorker) {
//
//        upWorker.work1()
//                .subscribeOn(upWorker.backgroundThread ? Schedulers.from(ThreadManager.getUploadsericePool()) : Schedulers.trampoline())
//                .flatMap(upWorker.work2())
//                .subscribe(upWorker.callback == null ? upWorker.defaultCallback() : upWorker.callback);
//    }

    public UpChain beginWith(@NotNull UpWorker... upWorker) {
        return new UpChain(this, new ArrayList<UpWorker>(Arrays.asList(upWorker)));
    }

    public UpChain beginWith(@NotNull List<UpWorker> upWorker) {
        return new UpChain(this, upWorker);
    }


    public void action(UpGroup upGroup) {
//        Observable.zip(upGroup.getWork1())
//                .flatMap(upGroup.getWork2())
//                .subscribe(new ErrorHandleSubscriber<Object>(ArmsUtils.obtainAppComponentFromContext(GodApplication.getInstance()).rxErrorHandler()) {
//                    @Override
//                    public void onNext(Object o) {
//
//                    }
//                });
    }
}
