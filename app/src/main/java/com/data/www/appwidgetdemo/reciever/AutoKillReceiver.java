package com.data.www.appwidgetdemo.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.data.www.appwidgetdemo.services.UpdateService;

//接收清除程序的广播
public class AutoKillReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent intent2 = new Intent();
        intent2.setClass(context, UpdateService.class);
        intent2.setAction(UpdateService.ACTION_CLEAR);
        context.startService(intent2);
    }
}
