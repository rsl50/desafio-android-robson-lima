package br.com.accenture.desafio_android_robson_lima.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ComicBookResult implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("urls")
    @Expose
    private ArrayList<Url> urls;

    @SerializedName("prices")
    @Expose
    private ArrayList<Price> prices;

    @SerializedName("thumbnail")
    @Expose
    Thumbnail thumbnail;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Price> getPrices() {
        return prices;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    protected ComicBookResult(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        urls = in.createTypedArrayList(Url.CREATOR);
        prices = in.createTypedArrayList(Price.CREATOR);
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
    }

    public static final Creator<ComicBookResult> CREATOR = new Creator<ComicBookResult>() {
        @Override
        public ComicBookResult createFromParcel(Parcel in) {
            return new ComicBookResult(in);
        }

        @Override
        public ComicBookResult[] newArray(int size) {
            return new ComicBookResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeTypedList(urls);
        dest.writeTypedList(prices);
        dest.writeParcelable(thumbnail, flags);
    }
}
