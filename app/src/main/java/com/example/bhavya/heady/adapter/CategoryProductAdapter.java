package com.example.bhavya.heady.adapter;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bhavya.heady.Fragments.CategoryProductFragment;
import com.example.bhavya.heady.Fragments.ProductVariantFragment;
import com.example.bhavya.heady.R;
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

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.MyViewHolder> {


    ProductModel entity;
    List<ProductModel> lisRoot = new ArrayList<>();
    public ResponseModel responseModel;
    public CategoryModel categoryModel;
    public ProductModel productModel;
    public Activity myActivity;
    public CategoryProductFragment categoryProductFragment;


    public CategoryProductAdapter(CategoryProductFragment categoryProductFragment, List<ProductModel> _listRoot, Activity myActivity) {
        this.categoryProductFragment = categoryProductFragment;
        lisRoot = _listRoot;
        this.myActivity = myActivity;

    }

    @Override
    public CategoryProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_category_item, parent, false);
        return new CategoryProductAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        entity = lisRoot.get(position);
        holder.tvName.setText("Name : " + entity.name);
        holder.tvId.setText("ID :" + entity.id);
        holder.btnVariant.setTag(entity);
    }

    @Override
    public int getItemCount() {
        return lisRoot.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvId, tvName, tvDateAdded;
        public Button btnVariant;

        public MyViewHolder(View view) {
            super(view);
            tvId = (TextView) view.findViewById(R.id.tvProductId);
            tvName = (TextView) view.findViewById(R.id.tvProductsName);
            tvDateAdded = (TextView) view.findViewById(R.id.tvDataAdded);
            btnVariant = (Button) view.findViewById(R.id.btnVariant);
             btnVariant.setOnClickListener(new View.OnClickListener() {@Override
             public void onClick(View v) {
             ProductVariantFragment productVariantFragment = new ProductVariantFragment();
             ProductModel productModel = (ProductModel) v.getTag();
             Bundle bundle = new Bundle();
             bundle.putParcelable("DATA", productModel);
             FragmentTransaction fragmentTransaction = myActivity.getFragmentManager().beginTransaction();
             fragmentTransaction.add(R.id.ll_rv, productVariantFragment);
             fragmentTransaction.addToBackStack("MainActivityAdapter");
             fragmentTransaction.commit();
             productVariantFragment.setArguments(bundle);
                                              }
                                          }
            );
        }
    }
}
