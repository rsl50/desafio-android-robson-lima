package br.com.accenture.desafio_android_robson_lima.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComicBook implements Parcelable {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("etag")
    @Expose
    private String etag;

    @SerializedName("data")
    @Expose
    private ComicBookData data;

    public String getString() {
        return etag;
    }

    public ComicBookData getData() {
        return data;
    }

    protected ComicBook(Parcel in) {
        code = in.readInt();
        status = in.readString();
        etag = in.readString();
        data = in.readParcelable(ComicBookData.class.getClassLoader());
    }

    public static final Creator<ComicBook> CREATOR = new Creator<ComicBook>() {
        @Override
        public ComicBook createFromParcel(Parcel in) {
            return new ComicBook(in);
        }

        @Override
        public ComicBook[] newArray(int size) {
            return new ComicBook[size];
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
}
