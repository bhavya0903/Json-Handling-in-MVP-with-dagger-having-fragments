package com.example.bhavya.heady.home_module;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bhavya.heady.Fragments.CategoryProductFragment;
import com.example.bhavya.heady.R;

import java.util.ArrayList;
import java.util.List;

import models.CategoryModel;
import models.ProductModel;
import models.RankingsModel;
import models.ResponseModel;

/**
 * Created by skarim on 1/19/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.MyViewHolder> {

    CategoryModel entity;
    List<CategoryModel> lisRoot = new ArrayList<>();
    Context context;
    MainActivity activity;
    public ResponseModel responseModel;
    public CategoryModel categoryModel;
    List<RankingsModel> rankingsModels;


    public MainActivityAdapter(Context context, List<CategoryModel> _listRoot) {
        this.context = context;
        lisRoot = _listRoot;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daywise_data_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        entity = lisRoot.get(position);

        holder.tvCode.setText("Name : " + entity.name);

        holder.tvVariant.setTag(entity);



    }

    @Override
    public int getItemCount() {

        return lisRoot.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCode, tvVariant, tvLow, tvHigh, tvDay, tvDate;

        public MyViewHolder(View view) {
            super(view);
            tvCode = (TextView) view.findViewById(R.id.tvCode);
            tvVariant = (TextView) view.findViewById(R.id.tvVariant);

            tvVariant.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
            CategoryProductFragment categoryProductFragment = new CategoryProductFragment();
            CategoryModel categoryModel = (CategoryModel) v.getTag();
            Bundle bundle = new Bundle();
            bundle.putParcelable("DATA", categoryModel);
            categoryProductFragment.setArguments(bundle);
            MainActivity myActivity = (MainActivity) context;
            Toast.makeText(context, "Category product clicked..!!!!", Toast.LENGTH_LONG).show();
             FragmentTransaction transaction =myActivity.getFragmentManager().beginTransaction();
             transaction.add(R.id.ll_rv, categoryProductFragment);
             transaction.addToBackStack("MainActivityAdapter");
             transaction.commit();

                                             }
                                         }
            );
        }

    }
}
