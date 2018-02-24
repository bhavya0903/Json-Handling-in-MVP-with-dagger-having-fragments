package com.example.bhavya.heady.dagger.component;

import com.example.bhavya.heady.dagger.module.AppModule;
import com.example.bhavya.heady.dagger.module.CorePresenterModule;
import com.example.bhavya.heady.home_module.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Bhavya on 12-02-2018.
 */

@Singleton
@Component(modules = {AppModule.class, CorePresenterModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}

