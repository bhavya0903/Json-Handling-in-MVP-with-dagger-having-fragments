package com.example.bhavya.heady.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhavya.heady.R;
import com.example.bhavya.heady.adapter.CategoryProductAdapter;
import com.example.bhavya.heady.home_module.MainActivity;
import com.example.bhavya.heady.home_module.MainActivityAdapter;

import java.util.ArrayList;
import java.util.List;

import models.CategoryModel;
import models.ProductModel;
import models.ResponseModel;

/**
 * Created by Bhavya on 15-02-2018.
 */

public class CategoryProductFragment extends Fragment {

    public RecyclerView rvCategoryProduct;
    public Context context;
    public ResponseModel responseModel;
    CategoryModel categoryModel;
    public CategoryProductAdapter categoryProductAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryModel = getArguments().getParcelable("DATA");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_category,
                container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        rvCategoryProduct = (RecyclerView) getView().findViewById(R.id.rvProductCategory);
        rvCategoryProduct.setHasFixedSize(true);
        rvCategoryProduct.setLayoutManager(new LinearLayoutManager(context));
        setData();


    }


    public void setData() {
        if (categoryModel != null && categoryModel.products != null && categoryModel.products.size() > 0) {
            categoryProductAdapter = new CategoryProductAdapter(this, categoryModel.products, getActivity());
            rvCategoryProduct.setAdapter(categoryProductAdapter);
        } else {
            showToastMessage("No Data Found");
        }

    }

    private void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

}