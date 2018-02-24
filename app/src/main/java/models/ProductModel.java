package models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Bhavya on 13-02-2018.
 */

public class ProductModel implements Parcelable {

    public List<VarientModel> variants;
    public TaxModel tax;
    public int id;
    public String name;
    public String date_added;

    protected ProductModel(Parcel in) {
        variants = in.createTypedArrayList(VarientModel.CREATOR);
        tax = in.readParcelable(TaxModel.class.getClassLoader());
        id = in.readInt();
        name = in.readString();
        date_added = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(variants);
        parcel.writeParcelable(tax, i);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(date_added);
    }
}
