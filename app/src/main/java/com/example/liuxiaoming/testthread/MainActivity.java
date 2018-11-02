package com.example.liuxiaoming.testthread;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.liuxiaoming.upwork.TestService;
import com.example.liuxiaoming.upwork.UpData;
import com.example.liuxiaoming.upwork.UpDirector;
import com.example.liuxiaoming.upwork.UpResult;
import com.example.liuxiaoming.upwork.Utils;
import com.example.liuxiaoming.upwork.test.TestWork2;
import com.example.liuxiaoming.upwork.test.TestWorker;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String COUNT = "COUNT";
    public static int count = 0;

    Button button;
    private String tag  ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(tag,"启动！！");

        startServiceAndRegisteReceiver(this);
        startServiceAndRegisteReceiver(this);
        button = findViewById(R.id.btn_test);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        count++;

        UpData upData = new UpData.Builder()
                .putString(COUNT, count+"")
                .build();

        Log.d(tag, "=====================点击促发=====================");

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
                Log.d("MainActivity","收到 "+s+" thread-->"+Utils.getThreadSignature());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 启动相关服务和设置相关监听
     */
    public void startServiceAndRegisteReceiver(Context context) {
        TestService.startUpload(context);

        TestService uploadService = TestService.getInstance();

        uploadService.startUpNow();
    }
}
