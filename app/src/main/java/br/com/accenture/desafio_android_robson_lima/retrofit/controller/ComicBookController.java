package br.com.accenture.desafio_android_robson_lima.retrofit.controller;

import android.util.Log;
import android.view.View;

import java.util.List;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.model.ComicBook;
import br.com.accenture.desafio_android_robson_lima.model.ComicBookResult;
import br.com.accenture.desafio_android_robson_lima.model.HeroResult;
import br.com.accenture.desafio_android_robson_lima.retrofit.HeroRetrofit;
import br.com.accenture.desafio_android_robson_lima.retrofit.service.HeroService;
import br.com.accenture.desafio_android_robson_lima.ui.activity.HeroDetailsActivity;
import br.com.accenture.desafio_android_robson_lima.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicBookController {

    private List<ComicBookResult> comicBookList;

    public List<ComicBookResult>  getHeroComics (int heroId) {
        HeroService service = new HeroRetrofit().getHeroService();
        Call<ComicBook> call = service.getComicBooks(heroId);

        call.enqueue(new Callback<ComicBook>() {
            @Override
            public void onResponse(Call<ComicBook> call, Response<ComicBook> response) {
                if (response.isSuccessful()) {
                    comicBookList = response.body().getData().getComicBookResult();
                } else {
                    Log.e("DesafioAndroidApp", "No sucess - Response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ComicBook> call, Throwable t) {
                Log.e("DesafioAndroidApp", "OnFailure - Error: " + t.getMessage());
            }
        });

        return comicBookList;
    }
}
