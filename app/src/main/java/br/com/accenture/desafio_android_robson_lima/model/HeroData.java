package br.com.accenture.desafio_android_robson_lima.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HeroData implements Parcelable {

    @SerializedName("ofsset")
    @Expose
    private int offset;

    @SerializedName("limit")
    @Expose
    private int limit;

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("results")
    @Expose
    private ArrayList<HeroResult> heroResult;

    public ArrayList<HeroResult> getHeroResult() {
        return heroResult;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return  total;
    }

    protected HeroData(Parcel in) {
        offset = in.readInt();
        limit = in.readInt();
        total = in.readInt();
        count = in.readInt();
        heroResult = in.createTypedArrayList(HeroResult.CREATOR);
    }

    public static final Creator<HeroData> CREATOR = new Creator<HeroData>() {
        @Override
        public HeroData createFromParcel(Parcel in) {
            return new HeroData(in);
        }

        @Override
        public HeroData[] newArray(int size) {
            return new HeroData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(offset);
        dest.writeInt(limit);
        dest.writeInt(total);
        dest.writeInt(count);
        dest.writeTypedList(heroResult);
    }
}
