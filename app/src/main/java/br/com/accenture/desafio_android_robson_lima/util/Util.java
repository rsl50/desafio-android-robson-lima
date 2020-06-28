package br.com.accenture.desafio_android_robson_lima.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static br.com.accenture.desafio_android_robson_lima.BuildConfig.PRIVATE_KEY;
import static br.com.accenture.desafio_android_robson_lima.BuildConfig.PUBLIC_KEY;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.NO_DESCRIPTION;

public class Util {

    private static ProgressDialog loading;

    public static String getMD5Hash(Long timeStamp) {
        String input = timeStamp.toString() + PRIVATE_KEY + PUBLIC_KEY;

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert md != null;
        BigInteger hash = new BigInteger(1, md.digest(input.getBytes()));

        return hash.toString(16);
    }

    public static void getImage(Context context, ImageView imageView, String imageUrl){
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(imageUrl)
                .into(imageView);
    }

    public static void showDialog(Context context, String title) {
        loading = new ProgressDialog(context);
        loading.setTitle(title);
        loading.setIndeterminate(true);
        loading.setCanceledOnTouchOutside(false);
        loading.show();

    }

    public static void hideDialog() {
        loading.dismiss();
    }

    public static String getDescription(String description) {
        if (description.length() > 0) {
            return description;
        } else {
            return NO_DESCRIPTION;
        }
    }
}
