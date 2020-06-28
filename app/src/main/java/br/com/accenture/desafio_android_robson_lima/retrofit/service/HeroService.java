package br.com.accenture.desafio_android_robson_lima.retrofit.service;

import br.com.accenture.desafio_android_robson_lima.model.Hero;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HeroService {
    @GET("characters")
    Call<Hero> getHeroesList();
}
