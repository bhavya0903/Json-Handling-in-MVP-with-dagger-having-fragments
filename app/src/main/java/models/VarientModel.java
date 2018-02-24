package models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bhavya on 13-02-2018.
 */

public class VarientModel  implements Parcelable{

    public int id;
    public String color;
    public int size;
    public int price;

    protected VarientModel(Parcel in) {
        id = in.readInt();
        color = in.readString();
        size = in.readInt();
        price = in.readInt();
    }

    public static final Creator<VarientModel> CREATOR = new Creator<VarientModel>() {
        @Override
        public VarientModel createFromParcel(Parcel in) {
            return new VarientModel(in);
        }

        @Override
        public VarientModel[] newArray(int size) {
            return new VarientModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(color);
        parcel.writeInt(size);
        parcel.writeInt(price);
    }
}
