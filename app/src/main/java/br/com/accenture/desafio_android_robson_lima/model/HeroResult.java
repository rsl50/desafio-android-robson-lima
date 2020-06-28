package br.com.accenture.desafio_android_robson_lima.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HeroResult implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("modified")
    @Expose
    private String modified;

    @SerializedName("thumbnail")
    @Expose
    Thumbnail thumbnail;

    @SerializedName("urls")
    @Expose
    ArrayList<Url> urls;

    @SerializedName("prices")
    @Expose
    ArrayList<Price> prices;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    protected HeroResult(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        modified = in.readString();
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        prices = in.createTypedArrayList(Price.CREATOR);
    }

    public static final Creator<HeroResult> CREATOR = new Creator<HeroResult>() {
        @Override
        public HeroResult createFromParcel(Parcel in) {
            return new HeroResult(in);
        }

        @Override
        public HeroResult[] newArray(int size) {
            return new HeroResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(modified);
        dest.writeParcelable(thumbnail, flags);
        dest.writeTypedList(prices);
    }
}
