package com.example.personal.myapplication2;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
//    static Tab1 tab1=null;
    static GPSTracker1 gpsTrk=null;
    public MyService() {
        Log.i("checkings1","came here");
    }
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
  //      if(tab1==null){
            //tab1 = new Tab1();
        Log.i("checking1","came here");
            gpsTrk=new GPSTracker1(MyService.this);
        Log.i("checking2","came here");
          //  IntentFilter inf = new IntentFilter();
            //inf.addAction("android.intent.action.);
            //inf.addAction("android.intent.action.SCREEN_OFF");
            //registerReceiver(r1, inf);
    //    }
    }

    @Override
    public void onDestroy() {
        //if(r1!=null){
          //  unregisterReceiver(r1);
            //r1=null;
            gpsTrk.stopUsingGPS();
            gpsTrk=null;
        //}
        super.onDestroy();
    }
}
