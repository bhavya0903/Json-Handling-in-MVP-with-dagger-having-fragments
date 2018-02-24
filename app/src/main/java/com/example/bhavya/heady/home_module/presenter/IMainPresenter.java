package com.example.bhavya.heady.home_module.presenter;


import com.example.bhavya.heady.home_module.MainActivity;
import com.example.bhavya.heady.home_module.view.IMainView;

/**
 * Created by skarim on 4/22/17.
 */

public interface IMainPresenter {
    void setView(IMainView view, MainActivity context);
    void getJsonData();
}
