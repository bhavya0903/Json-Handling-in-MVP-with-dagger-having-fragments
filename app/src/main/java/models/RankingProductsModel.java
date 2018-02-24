package models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bhavya on 14-02-2018.
 */

public class RankingProductsModel implements Parcelable {
    public int id;
    public int view_count;
    public int order_count;
    public int shares;


    protected RankingProductsModel(Parcel in) {
        id = in.readInt();
        view_count = in.readInt();
        order_count = in.readInt();
        shares = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(view_count);
        dest.writeInt(order_count);
        dest.writeInt(shares);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RankingProductsModel> CREATOR = new Creator<RankingProductsModel>() {
        @Override
        public RankingProductsModel createFromParcel(Parcel in) {
            return new RankingProductsModel(in);
        }

        @Override
        public RankingProductsModel[] newArray(int size) {
            return new RankingProductsModel[size];
        }
    };
}
