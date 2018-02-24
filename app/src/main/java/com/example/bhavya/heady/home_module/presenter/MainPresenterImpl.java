package com.example.bhavya.heady.home_module.presenter;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bhavya.heady.base.MyApplication;
import com.example.bhavya.heady.entities.DayWiseDataEntity;
import com.example.bhavya.heady.home_module.MainActivity;
import com.example.bhavya.heady.home_module.view.IMainView;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import models.CategoryModel;
import models.ResponseModel;


public class MainPresenterImpl implements IMainPresenter {

    @Inject
    IMainView view;

    MainActivity context;


    @Override
    public void setView(IMainView view, MainActivity context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getJsonData() {
        String weatherURL = "https://stark-spire-93433.herokuapp.com/json";
        try {
            view.controlProgressBar(true);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    weatherURL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            view.controlProgressBar(false);
                            if (response != null) {
                                showLog(response.toString());
                                ResponseModel responseModel = new Gson().fromJson(response.toString(), ResponseModel.class);
                                view.onSuccess(responseModel);
                            } else {
                                view.showErrorMessage("No Data Found");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    view.controlProgressBar(false);

                    if (error != null && error.networkResponse != null) {
                        view.onFaillure(error);
                    } else {
                        view.showErrorMessage("An unexpected Error occured");
                    }
                }
            });

            request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().addToRequestQueue(request, "GET_WEATHER_DATA");
        } catch (Exception e) {
            view.controlProgressBar(false);
            view.showErrorMessage(e.getMessage());
        }

    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    };

    private void showLog(String message) {
        Log.d("skm", message == null ? "" : message);
    }

    private void showToastMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
