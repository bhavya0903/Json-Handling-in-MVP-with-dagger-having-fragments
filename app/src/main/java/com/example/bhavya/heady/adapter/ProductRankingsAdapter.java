package com.example.bhavya.heady.adapter;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bhavya.heady.Fragments.ProductVariantFragment;
import com.example.bhavya.heady.Fragments.RankingFragment;
import com.example.bhavya.heady.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import models.ProductModel;
import models.RankingProductsModel;
import models.RankingsModel;

/**
 * Created by Bhavya on 19-02-2018.
 */


public class ProductRankingsAdapter extends RecyclerView.Adapter<ProductRankingsAdapter.MyViewHolder> {
    public Activity myActivity;
    RankingFragment rankingFragment;
    RankingProductsModel entity;
    ArrayList<Integer> productModelKey;
    ArrayList<ProductModel> productModelHashMap;
    List<RankingProductsModel> lisRoot = new ArrayList<>();

    private static final int TYPE_MOST_VIEWED = 0;
    private static final int TYPE_MOST_ORDERED = 1;
    private static final int TYPE_SHARED = 2;

    public ProductRankingsAdapter(RankingFragment rankingFragment, List<RankingProductsModel> _listRoot, Activity myActivity,
                                  ArrayList<Integer> productModelKey, ArrayList<ProductModel> productModelHashMap, int type) {
        this.rankingFragment = rankingFragment;
        lisRoot = _listRoot;
        this.myActivity = myActivity;
        shortList();
        this.productModelHashMap = productModelHashMap;
        this.productModelKey = productModelKey;
        this.type = type;
    }

    int type;

    private void shortList() {
        Collections.sort(lisRoot, new Comparator<RankingProductsModel>() {
            @Override
            public int compare(RankingProductsModel l, RankingProductsModel r) {
                if (l.view_count > r.view_count)
                    return 1;
                else if (l.view_count < r.view_count)
                    return -1;
                return 0;
            }
        });
    }


    @Override
    public ProductRankingsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_ranking_item, parent, false);
        return new ProductRankingsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductRankingsAdapter.MyViewHolder holder, int position) {
        entity = lisRoot.get(position);
        int productIndex = productModelKey.indexOf(entity.id);
        ProductModel productModel = productModelHashMap.get(productIndex);
        if (type == TYPE_MOST_VIEWED)
            holder.tvViewCount.setText("View Count : " + entity.view_count);
        else if (type == TYPE_MOST_ORDERED)
            holder.tvViewCount.setText("Most Ordered : " + entity.order_count);
        else if(type == TYPE_SHARED)
            holder.tvViewCount.setText("Shared : " + entity.shares);
        holder.tvId.setText("Product :" + productModel.name);
    }

    @Override
    public int getItemCount() {
        return lisRoot.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvId, tvViewCount;

        public MyViewHolder(View view) {
            super(view);
            tvId = (TextView) view.findViewById(R.id.tvProductId);
            tvViewCount = (TextView) view.findViewById(R.id.tvProductsViewcount);

        }
    }
}

