package com.example.bhavya.heady.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bhavya.heady.R;
import com.example.bhavya.heady.adapter.CategoryProductAdapter;
import com.example.bhavya.heady.adapter.ProductRankingsAdapter;

import java.util.ArrayList;

import models.ProductModel;
import models.RankingsModel;

/**
 * Created by Bhavya on 17-02-2018.
 */

public class RankingFragment extends Fragment {

    RecyclerView rvRankingProduct;
    public Context context;
    RankingsModel rankingsModel;
    ProductRankingsAdapter productRankingsAdapter;
    ArrayList<ProductModel> productModelHashMap;
    ArrayList<Integer> productModelKey;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rankingsModel = getArguments().getParcelable("DATA");
            productModelKey = getArguments().getIntegerArrayList("PRODUCTDATAKEY");
            productModelHashMap = getArguments().getParcelableArrayList("PRODUCTDATAMODEL");
            type = getArguments().getInt("TYPE");

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ranking_product,
                container, false);
//            super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        init();
    }
int type;
    private void init() {
        rvRankingProduct = (RecyclerView) getView().findViewById(R.id.rvProductRanking);
        rvRankingProduct.setHasFixedSize(true);
        rvRankingProduct.setLayoutManager(new LinearLayoutManager(context));
        initData();
    }


    public void initData() {
        if (rankingsModel != null && rankingsModel.products != null && rankingsModel.products.size() > 0) {
            productRankingsAdapter = new ProductRankingsAdapter(this, rankingsModel.products, getActivity(), productModelKey, productModelHashMap, type);
            rvRankingProduct.setAdapter(productRankingsAdapter);
        } else {
            showToastMessage("No Data Found");
        }
    }

    private void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
