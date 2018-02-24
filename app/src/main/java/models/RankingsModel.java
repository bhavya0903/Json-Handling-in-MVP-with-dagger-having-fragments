package models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhavya on 14-02-2018.
 */

public class RankingsModel implements Parcelable {

    public String ranking;
    public List<RankingProductsModel> products;


    protected RankingsModel(Parcel in) {
        ranking = in.readString();
        products = in.createTypedArrayList(RankingProductsModel.CREATOR);
    }

    public static final Creator<RankingsModel> CREATOR = new Creator<RankingsModel>() {
        @Override
        public RankingsModel createFromParcel(Parcel in) {
            return new RankingsModel(in);
        }

        @Override
        public RankingsModel[] newArray(int size) {
            return new RankingsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ranking);
        parcel.writeTypedList(products);
    }
}
