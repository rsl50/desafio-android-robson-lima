package br.com.accenture.desafio_android_robson_lima.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.accenture.desafio_android_robson_lima.R;

import static br.com.accenture.desafio_android_robson_lima.BuildConfig.PRIVATE_KEY;
import static br.com.accenture.desafio_android_robson_lima.BuildConfig.PUBLIC_KEY;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.BAD_GATEWAY;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.BAD_REQUEST;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.CONFLICT;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.FORBIDDEN;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.GATEWAY_TIMEOUT;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.NOT_FOUND;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.NO_DESCRIPTION;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.UNAUTHORIZED;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.UNKNOW_ERROR;

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

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting() && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
        return isConnected && isOnline();
    }

    private static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean commErrorDialog(Context context, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final boolean[] choice = {false};

        builder.setTitle(title)
                .setMessage(message + context.getString(R.string.retry_string))
                .setPositiveButton(context.getString(R.string.option_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choice[0] = true;
                        dialog.dismiss();
                        Util.showDialog(context, context.getString(R.string.loading_message));
                    }
                })
                .setNegativeButton(R.string.option_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity) context).finish();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

        return choice[0];
    }

    public static String getApiError (int erroCode) {
        String errorString;

        switch (erroCode) {
            case 400: {
                errorString = BAD_REQUEST;
                break;
            }

            case 401: {
                errorString = UNAUTHORIZED;
                break;
            }

            case 403: {
                errorString = FORBIDDEN;
                break;
            }

            case 404: {
                errorString = NOT_FOUND;
                break;
            }

            case 409: {
                errorString = CONFLICT;
                break;
            }

            case 502: {
                errorString = BAD_GATEWAY;
                break;
            }

            case 504: {
                errorString = GATEWAY_TIMEOUT;
                break;
            }

            default: {
                errorString = UNKNOW_ERROR;
            }
        }

        return errorString;
    }
}
