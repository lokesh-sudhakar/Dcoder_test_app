package com.example.decoder_app;

import android.app.Application;

import com.example.decoder_app.di.AppComponent;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public class DcoderApplication extends Application {

    public static DcoderApplication instance;
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = AppComponent.Initializer.initialize(instance);
    }

    public static DcoderApplication getInstance(){
        return  instance;
    }


    public AppComponent getAppComponent() {
        if (appComponent ==null) {
            appComponent = AppComponent.Initializer.initialize(instance);
        }
        return appComponent;
    }



}
