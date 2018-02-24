package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bhavya on 13-02-2018.
 */

public class ChildCategoryModel  implements Parcelable{

    protected ChildCategoryModel(Parcel in) {
    }

    public static final Creator<ChildCategoryModel> CREATOR = new Creator<ChildCategoryModel>() {
        @Override
        public ChildCategoryModel createFromParcel(Parcel in) {
            return new ChildCategoryModel(in);
        }

        @Override
        public ChildCategoryModel[] newArray(int size) {
            return new ChildCategoryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
