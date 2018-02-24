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
import android.widget.Toast;

import com.example.bhavya.heady.R;
import com.example.bhavya.heady.adapter.CategoryProductAdapter;
import com.example.bhavya.heady.adapter.ProductVariantAdapter;

import models.CategoryModel;
import models.ProductModel;
import models.ResponseModel;
import models.VarientModel;

/**
 * Created by Bhavya on 16-02-2018.
 */

public class ProductVariantFragment extends Fragment {
    public RecyclerView rvProductVariant;
    public Context context;
    public ResponseModel responseModel;
    CategoryModel categoryModel;
    public ProductVariantAdapter productVariantAdapter;
    ProductModel productModel;
    VarientModel varientModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productModel = getArguments().getParcelable("DATA");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.variant_product,
                container, false);
//            super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {

        rvProductVariant = (RecyclerView) getView().findViewById(R.id.rvVariantProduct);
        rvProductVariant.setHasFixedSize(true);
        rvProductVariant.setLayoutManager(new LinearLayoutManager(context));
        setData();

    }

    public void setData() {
        if (productModel != null && productModel.variants != null && productModel.variants.size() > 0) {
            productVariantAdapter = new ProductVariantAdapter(this, productModel.variants);
            rvProductVariant.setAdapter(productVariantAdapter);
        } else {
            showToastMessage("No Data Found");
        }

    }

    private void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}