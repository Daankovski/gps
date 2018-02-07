package com.example.daan.gps.Classes;

import android.app.Application;

/**
 * Created by Daan on 07/02/2018.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

        @Override
        public void onCreate() {
            super.onCreate();

            mInstance = this;
        }

        public static synchronized MyApplication getInstance(){
            return mInstance;
        }

        public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener){
            ConnectivityReceiver.connectivityRecieverListener = listener;
        }
}
