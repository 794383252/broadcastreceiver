package com.example.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//广播接收器不允许创建线程，所以onReceive中不能添加过多的逻辑或者是耗时的操作
//广播接收器可以被其他应用接受，所以可以使用本地广播
public class MainActivity extends Activity implements View.OnClickListener{

    Button button;
    private IntentFilter filter;
    private netWorkChangeReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        filter=new IntentFilter();
        filter.addAction("android.net.conn.,");
        receiver=new netWorkChangeReceiver();
        registerReceiver(receiver,filter);
    }

    private void init()
    {
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("ln","广播发送成功");
        Intent intent=new Intent("myBroadcast");
        //发送标准广播
//        sendBroadcast(intent);
        //发送有序广播
        sendOrderedBroadcast(intent,null);
    }

    //动态注册广播记得删除
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    //动态注册广播
    class netWorkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager= (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=manager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isAvailable())
            {
                Log.i("ln","network is Available");
            }else {
                Log.i("ln","network is unAvailable");
            }
        }

    }

    //静态注册广播
   public class bootCompleteReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("ln","boot complete");
        }
    }

}
