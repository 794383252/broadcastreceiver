package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/6.
 */
public class myBroadCastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ln","it is myBroadCastReceiver");
        //阻断广播继续传输
        abortBroadcast();
    }
}
