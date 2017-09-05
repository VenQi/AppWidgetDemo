package com.data.www.appwidgetdemo.reciever;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import com.data.www.appwidgetdemo.services.UpdateService;

/**
 * Created by qwh on 2017/9/5.
 */

public class MyProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        Intent startUpdateIntent = new Intent(context, UpdateService.class);
        context.startService(startUpdateIntent);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Intent stopUpdateIntent = new Intent(context, UpdateService.class);
        context.stopService(stopUpdateIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Intent startUpdateIntent = new Intent(context, UpdateService.class);
        context.startService(startUpdateIntent);
    }
}
