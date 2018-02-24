package models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bhavya on 13-02-2018.
 */

public class CategoryModel implements Parcelable {

    public List<ProductModel> products;
    public List<Integer> child_categories;
    public int id;
    public String name;

    protected CategoryModel(Parcel in) {
        products = in.createTypedArrayList(ProductModel.CREATOR);
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(products);
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
