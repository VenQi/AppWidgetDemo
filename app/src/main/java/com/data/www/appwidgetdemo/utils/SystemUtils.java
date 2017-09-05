package com.data.www.appwidgetdemo.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by qwh on 2017/9/5.
 */

public class SystemUtils {
    //获取当前正在运行的进程数目
    public static int getRunningTaskCount(Context context){
        return (int) (Math.random()*100);
    }
    //获取当前可用的内存数,单位M
    public static double getAvaliMem(Context context){
        return Math.random()*3000;
    }

    public static void clearBackground(final ClearListener listener) {
        Log.e("Doing job","clearing...");
        listener.onStart();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            listener.onDoingjob();
                            Log.e("clearBg","start");
                            Thread.sleep(5000);
                            Log.e("clearBg","finish");
                            listener.onFinish("success!");
                        }catch (Exception e){
                            e.printStackTrace();
                            listener.onFinish(e.getMessage());
                        }
                    }
                }
        ).start();
    }
    public interface ClearListener{
        public void onFinish(String result);
        public void onStart();
        public void onDoingjob();
    }
}
