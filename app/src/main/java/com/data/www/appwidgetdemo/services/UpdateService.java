package com.data.www.appwidgetdemo.services;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.data.www.appwidgetdemo.R;
import com.data.www.appwidgetdemo.reciever.AutoKillReceiver;
import com.data.www.appwidgetdemo.reciever.MyProvider;
import com.data.www.appwidgetdemo.utils.SystemUtils;

import java.util.Timer;
import java.util.TimerTask;

public class UpdateService extends Service {
    private Timer timer;
    private TimerTask task;
    public static final  String ACTION_CLEAR = "self.intent.action.clearprocess";
    RemoteViews remoteViews;

    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (null!=intent&&ACTION_CLEAR.equals(intent.getAction())){
            SystemUtils.clearBackground(new SystemUtils.ClearListener() {
                @Override
                public void onFinish(String result) {
                    changeWidgetStatus("finish");
                }

                @Override
                public void onStart() {
                    changeWidgetStatus("start");
                }

                @Override
                public void onDoingjob() {
                    changeWidgetStatus("doing");
                }
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void changeWidgetStatus(String status) {
        switch (status) {
            case "start":

                break;
            case "doing":

                break;
            case "finish":

                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                int runningTaskCount = SystemUtils.getRunningTaskCount(UpdateService.this);
                double avaliMem = SystemUtils.getAvaliMem(UpdateService.this);
                ComponentName name = new ComponentName(UpdateService.this, MyProvider.class);
                remoteViews = new RemoteViews(getPackageName(), R.layout.activity_main);

                //更新widget中的组件内容
                remoteViews.setTextViewText(R.id.tv_up,"正在运行的程序数："+runningTaskCount);
                remoteViews.setTextViewText(R.id.tv_down,"可用内存："+avaliMem+" M");

                //点击按钮事件处理
                Intent intent = new Intent(UpdateService.this, AutoKillReceiver.class);
                intent.setAction("self.intent.brocast.autokillprocess");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(UpdateService.this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.btn_clear,pendingIntent);

                //注意，一定要使用Appwidgetmanager处理widget
                AppWidgetManager awm = AppWidgetManager.getInstance(getApplicationContext());
                awm.updateAppWidget(name,remoteViews);
            }
        };
        timer.schedule(task,3000,2000);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        task.cancel();
        timer=null;
        task = null;
    }
}
