package com.example.bhavya.heady.base;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.example.bhavya.heady.dagger.component.DaggerNetComponent;
import com.example.bhavya.heady.dagger.component.NetComponent;
import com.example.bhavya.heady.dagger.module.AppModule;
import com.example.bhavya.heady.dagger.module.CorePresenterModule;

/**
 * Created by Bhavya on 12-02-2018.
 */

public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();

    NetComponent mNetComponent;
    private RequestQueue requestQueue;
    private static MyApplication instace;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .corePresenterModule(new CorePresenterModule())
                .build();

        instace = this;
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public static synchronized MyApplication getInstance() {

        return instace;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);

    }
    public <T> void removeFromRequestQue(String tag) {
        getRequestQueue().cancelAll(tag);

    }

}

