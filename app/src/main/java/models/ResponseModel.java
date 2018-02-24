package models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Bhavya on 13-02-2018.
 */

public class ResponseModel implements Parcelable{
    public List<CategoryModel> categories;
    public List<RankingsModel> rankings;


    protected ResponseModel(Parcel in) {
        categories = in.createTypedArrayList(CategoryModel.CREATOR);
        rankings = in.createTypedArrayList(RankingsModel.CREATOR);
    }

    public static final Creator<ResponseModel> CREATOR = new Creator<ResponseModel>() {
        @Override
        public ResponseModel createFromParcel(Parcel in) {
            return new ResponseModel(in);
        }

        @Override
        public ResponseModel[] newArray(int size) {
            return new ResponseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(categories);
        parcel.writeTypedList(rankings);
    }
}
