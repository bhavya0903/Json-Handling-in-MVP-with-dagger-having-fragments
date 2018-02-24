package com.example.bhavya.heady.home_module.view;

import com.android.volley.VolleyError;


import java.util.List;

import models.CategoryModel;
import models.ResponseModel;

/**
 * Created by skarim on 4/22/17.
 */

public interface IMainView {
    void controlProgressBar(boolean isShowProgressBar);
    void onSuccess(ResponseModel dayWiseDataList);
    void onFaillure(VolleyError error);

    void showErrorMessage(String errorMessage);
}
