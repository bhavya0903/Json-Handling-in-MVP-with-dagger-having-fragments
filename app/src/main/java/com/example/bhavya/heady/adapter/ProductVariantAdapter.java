package com.example.bhavya.heady.adapter;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bhavya.heady.Fragments.CategoryProductFragment;
import com.example.bhavya.heady.Fragments.ProductVariantFragment;
import com.example.bhavya.heady.R;
import com.example.bhavya.heady.home_module.MainActivity;

import java.util.ArrayList;
import java.util.List;

import models.CategoryModel;
import models.ProductModel;
import models.ResponseModel;
import models.VarientModel;

/**
 * Created by Bhavya on 16-02-2018.
 */

public class ProductVariantAdapter extends  RecyclerView.Adapter<ProductVariantAdapter.MyViewHolder> {


     VarientModel entity;
    List<VarientModel> lisRoot = new ArrayList<>();
    Context context;
    MainActivity activity;
    public ResponseModel responseModel;
    public CategoryModel categoryModel;
    public ProductModel productModel;
    public VarientModel varientModel;




    public ProductVariantAdapter(ProductVariantFragment  productVariantFragment, List<VarientModel> _listRoot) {
        this.context = context;
        lisRoot = _listRoot;

    }

    public ProductVariantAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.variant_product_item, parent, false);
        return new ProductVariantAdapter.MyViewHolder(itemView);
    }



    public void onBindViewHolder(MyViewHolder holder, int position) {
        entity = lisRoot.get(position);
        holder.tvId.setText("Name : " + entity.id);
        holder.tvPrice.setText("Name : " + entity.price);
        holder.tvSize.setText("Name : " + entity.size);
        holder.tvColor.setText("Name : " + entity.color);

        holder.tvId.setText("ID :" + entity.id);
    }

    @Override
    public int getItemCount() {
        return lisRoot.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvId,tvColor,tvSize,tvPrice;

        public MyViewHolder(View view) {
            super(view);
            tvId = (TextView) view.findViewById(R.id.tvVariantId);
            tvColor = (TextView) view.findViewById(R.id.tvVariantColor);
            tvSize = (TextView) view.findViewById(R.id.tvVariantSize);
            tvPrice = (TextView) view.findViewById(R.id.tvVariantPrice);
        }
    }
    
}
