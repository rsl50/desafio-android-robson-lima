package br.com.accenture.desafio_android_robson_lima.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail implements Parcelable {

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("extension")
    @Expose
    private String extension;

    private String thumbnailResource;

    public String getThumbnailResource() {
        return thumbnailResource = path + "." + extension;
    }

    protected Thumbnail(Parcel in) {
        path = in.readString();
        extension = in.readString();
        thumbnailResource = in.readString();
    }

    public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(extension);
        dest.writeString(thumbnailResource);
    }
}
