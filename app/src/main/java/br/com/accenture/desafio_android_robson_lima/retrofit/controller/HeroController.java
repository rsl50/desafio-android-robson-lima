package br.com.accenture.desafio_android_robson_lima.retrofit.controller;

import android.app.Activity;
import android.util.Log;

import java.util.List;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.model.Hero;
import br.com.accenture.desafio_android_robson_lima.model.HeroResult;
import br.com.accenture.desafio_android_robson_lima.retrofit.HeroRetrofit;
import br.com.accenture.desafio_android_robson_lima.retrofit.service.HeroService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroController {

    private List<HeroResult> heroResult;

    public List<HeroResult> getHeroes() {

        HeroService service = new HeroRetrofit().getHeroService();
        Call<Hero> call = service.getHeroesList();


        call.enqueue(new Callback<Hero>(){
            @Override
            public void onResponse(Call<Hero> call, Response<Hero> response) {
                if (response.isSuccessful()) {
                    heroResult = response.body().getHeroData().getHeroResult();
                } else {
                    Log.e("DesafioAndroidApp", "No sucess - Response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Hero> call, Throwable t) {
                Log.e("DesafioAndroidApp", "OnFailure - Error: " + t.getMessage());
            }
        });

        return heroResult;
    }
}
