package br.com.accenture.desafio_android_robson_lima.retrofit;


import java.io.IOException;

import br.com.accenture.desafio_android_robson_lima.retrofit.service.HeroService;
import br.com.accenture.desafio_android_robson_lima.util.Util;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static br.com.accenture.desafio_android_robson_lima.BuildConfig.PUBLIC_KEY;
import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.BASE_URL;


public class HeroRetrofit {

    private HeroService heroService;
    private static Retrofit retrofit;
    private Long timeStamp;

    public HeroRetrofit() {
        OkHttpClient client = configureClient();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        heroService = retrofit.create(HeroService.class);
    }

    private OkHttpClient configureClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        timeStamp = System.currentTimeMillis()/1000;

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("apikey", PUBLIC_KEY)
                                .addQueryParameter("ts", timeStamp.toString())
                                .addQueryParameter("hash", Util.getMD5Hash(timeStamp))
                                .build();

                        // Add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public HeroService getHeroService() {
        return heroService;
    }
}

