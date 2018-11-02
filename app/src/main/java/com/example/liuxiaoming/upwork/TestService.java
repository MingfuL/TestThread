package com.example.liuxiaoming.upwork;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.liuxiaoming.testthread.MainActivity;
import com.example.liuxiaoming.upwork.test.TestWork2;
import com.example.liuxiaoming.upwork.test.TestWorker;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.liuxiaoming.testthread.MainActivity.COUNT;

@Route(path = "/service/TestService")
public class TestService extends Service implements IProvider {

    static int countService = 0;

    public class MyBinder extends Binder {
        public TestService getUploadService() {
            return TestService.this;
        }
    }

    //    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    public final static String SERVICE_ACTION = "update.Service";
    public final static String SERVICE_ACTION_PHOTO_TIME = "verification.service";
    public static final String RECEIVER_MSG = "com.cestbon.bo.receiver.msg";
    private final String tag = TestService.class.getName();
    private final long DELAY_TIME = 1000 * 10; //两分钟

    Handler handler = new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ThreadManager.getUploadsericePool().submit(new Runnable() {
                @Override
                public void run() {
                    runUploadJob();
                }
            });
            handler.postDelayed(this, DELAY_TIME);
        }
    };
    // 暴露自己的实例,供activity调用
    private MyBinder mBinder = new MyBinder();

    public static void startUpload(Context context) {
        Intent intent = new Intent(context, TestService.class);
        context.startService(intent);
    }

    public static void init(TestService TestService) {

    }

    public static TestService getInstance() {
        ThreadManager.prepare();

        TestService TestService = (TestService) ARouter.getInstance().build("/service/TestService").navigation();
        TestService.init(TestService);

        return TestService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startUp();
        return START_NOT_STICKY;
    }

    /**
     * 开始上传
     */
    public void startUp() {
        Log.e(tag, "开始跑 job");
        handler.postDelayed(runnable, DELAY_TIME);

    }

    /**
     * 立即上传
     */
    public void startUpNow() {
//        handler.postDelayed(runnable, 0);
        ThreadManager.getUploadsericePool().submit(new Runnable() {
            @Override
            public void run() {
                runUploadJob();
            }
        });
    }

    @Override
    public void init(Context context) {

        Log.d(tag,"init" + context);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private void runUploadJob() {
        countService++;
        Log.e(tag,"=====================uploadService  Begin"+"-"+countService+"-"+"=====================");
        Log.e(tag,"UploadService开始跑啦～thread-->"+Utils.getThreadSignature()+"时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));
        test();
        Log.e(tag,"=====================uploadService  End=====================");
    }

    private void test() {

        UpData upData = new UpData.Builder()
                .putString(COUNT, "uploadService-"+countService)
                .build();

        Log.e(tag,"service test方法来啦～thread-->"+Utils.getThreadSignature()+"时间："+new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()));
        TestWorker worker = new TestWorker.Builder().setInputData(upData).backgroundThread(true).build();

        TestWork2 getDataWorker = new TestWork2.Builder().backgroundThread(true).build();

        UpResult action = UpDirector.getInstance()
                .beginWith(worker)
                .then(getDataWorker)
                .action();
        action.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.d(tag,"收到 "+s+" thread-->"+Utils.getThreadSignature());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
