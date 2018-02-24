package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bhavya on 13-02-2018.
 */

public class TaxModel  implements Parcelable{
    public String name;
    public double value;


    protected TaxModel(Parcel in) {
        name = in.readString();
        value = in.readDouble();
    }

    public static final Creator<TaxModel> CREATOR = new Creator<TaxModel>() {
        @Override
        public TaxModel createFromParcel(Parcel in) {
            return new TaxModel(in);
        }

        @Override
        public TaxModel[] newArray(int size) {
            return new TaxModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(value);
    }
}
