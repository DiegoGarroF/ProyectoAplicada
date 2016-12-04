package com.proyecto.aplicada.conectados;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

/**
 * Created by Garro on 03/12/2016.
 */

public class ServicioDeMsj extends Service {

    private Thread hilo = null;

    public ServicioDeMsj() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
    public int onStartCommand(final Intent intent, final int flags, int startId) {
        //mensaje("Entro al StartCommand");
        System.out.println("Hola entro al ONstart");
        if(hilo == null || !hilo.isAlive())
        {
            hilo = new Thread(new Runnable() {
                public void run() {
                    boolean estado=true;

                    while (estado) {
                        //
                        SystemClock.sleep(5000);
                    }
                }
            });
            hilo.start();

        }
        return START_STICKY;
    }
}
