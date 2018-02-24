package com.example.bhavya.heady.home_module;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.bhavya.heady.Fragments.RankingFragment;
import com.example.bhavya.heady.R;
import com.example.bhavya.heady.base.MyApplication;
import com.example.bhavya.heady.home_module.presenter.IMainPresenter;
import com.example.bhavya.heady.home_module.view.IMainView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import models.CategoryModel;
import models.ProductModel;
import models.RankingProductsModel;
import models.RankingsModel;
import models.ResponseModel;

public class MainActivity extends AppCompatActivity implements IMainView {

    @Inject
    IMainPresenter presenter;
    Toolbar toolbar;
    ProgressDialog progressDialog;
    RecyclerView rvWeatherData;
    MainActivityAdapter adapter;
    Button btnViewed, btnOrdered, btnShared;
    public ResponseModel responseModel;
    public BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (MyApplication.getInstance() != null) {
            MyApplication.getInstance().getNetComponent().inject(this);
        }
        initToolBar();
        initViews();
    }

    private void shortRanking() {
        ArrayList<RankingProductsModel> shortedMostViewdProducts = new ArrayList<>();
        shortedMostViewdProducts.addAll(responseModel.rankings.get(0).products);
        Collections.sort(shortedMostViewdProducts, new Comparator<RankingProductsModel>() {
            @Override
            public int compare(RankingProductsModel rankingProductsModel, RankingProductsModel t1) {
                if (rankingProductsModel.view_count > t1.view_count)
                    return 1;
                else if (rankingProductsModel.view_count < t1.view_count)
                    return -1;
                return 0;
            }
        });
    }

    ArrayList<Integer> productKeysHashMap;
    ArrayList<ProductModel> productModelHashMap;

    private void initProductMap(ResponseModel responseModel) {
        productKeysHashMap = new ArrayList<>();
        productModelHashMap = new ArrayList<>();
        for (CategoryModel categoryModel : responseModel.categories) {
            for (ProductModel productModel : categoryModel.products) {
                productKeysHashMap.add(productModel.id);
                productModelHashMap.add(productModel);
            }
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews() {
        presenter.setView(this, this);
        rvWeatherData = (RecyclerView) findViewById(R.id.rvWeatherData);
        rvWeatherData.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvWeatherData.setLayoutManager(llm);


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openRanking(0);
                    return true;
                case R.id.navigation_dashboard:
                    openRanking(1);
                    return true;
                case R.id.navigation_notifications:
                    openRanking(2);
                    return true;
            }
            return false;
        }
    };

    private void openRanking(int i) {
        RankingsModel rankingsModel = responseModel.rankings.get(i);
        RankingFragment rankingFragment = new RankingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("DATA", rankingsModel);
        bundle.putInt("TYPE", i);
        bundle.putIntegerArrayList("PRODUCTDATAKEY", productKeysHashMap);
        bundle.putParcelableArrayList("PRODUCTDATAMODEL", productModelHashMap);
        rankingFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        manageBackStack(fragmentManager);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ll_rv, rankingFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void manageBackStack(FragmentManager fragmentManager) {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        if (backStackCount > 0)
            fragmentManager.popBackStack();

    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.getJsonData();
    }

    @Override
    public void controlProgressBar(boolean isShowProgressBar) {
        if (isShowProgressBar) {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading, Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


    public void onSuccess(ResponseModel responseModel) {
        this.responseModel = responseModel;
        initProductMap(responseModel);
        shortRanking();
        //saveAllProducts();
        List<CategoryModel> dayWiseDataList = responseModel.categories;
        if (dayWiseDataList != null && dayWiseDataList.size() > 0) {
            adapter = new MainActivityAdapter(this, dayWiseDataList);
            rvWeatherData.setAdapter(adapter);

        } else {
            showToastMessage("No Data Found");
        }


    }


    @Override
    public void onFaillure(VolleyError error) {
        showToastMessage(error.getMessage());
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        showToastMessage(errorMessage);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    private void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
