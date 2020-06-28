package br.com.accenture.desafio_android_robson_lima.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ComicBookData implements Parcelable {

    @SerializedName("ofsset")
    private int offset;

    @SerializedName("limit")
    private int limit;

    @SerializedName("total")
    private int total;

    @SerializedName("count")
    private int count;

    @SerializedName("results")
    private ArrayList<ComicBookResult> comicBookResult;

    public ArrayList<ComicBookResult> getComicBookResult() {
        return comicBookResult;
    }

    protected ComicBookData(Parcel in) {
        offset = in.readInt();
        limit = in.readInt();
        total = in.readInt();
        count = in.readInt();
        comicBookResult = in.createTypedArrayList(ComicBookResult.CREATOR);
    }

    public static final Creator<ComicBookData> CREATOR = new Creator<ComicBookData>() {
        @Override
        public ComicBookData createFromParcel(Parcel in) {
            return new ComicBookData(in);
        }

        @Override
        public ComicBookData[] newArray(int size) {
            return new ComicBookData[size];
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
        dest.writeTypedList(comicBookResult);
    }
}
