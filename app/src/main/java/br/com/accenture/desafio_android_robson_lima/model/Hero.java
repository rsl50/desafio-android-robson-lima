package br.com.accenture.desafio_android_robson_lima.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Hero implements Parcelable {

    @SerializedName("code")
    private int code;

    @SerializedName("status")
    private String status;

    @SerializedName("etag")
    private String etag;

    @SerializedName("data")
    private HeroData data;

    protected Hero(Parcel in) {
        code = in.readInt();
        status = in.readString();
        etag = in.readString();
        data = in.readParcelable(HeroData.class.getClassLoader());
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(status);
        dest.writeString(etag);
        dest.writeParcelable(data, flags);
    }

    public HeroData getHeroData() {
        return data;
    }
}
