package br.com.accenture.desafio_android_robson_lima.retrofit.service;

import br.com.accenture.desafio_android_robson_lima.model.ComicBook;
import br.com.accenture.desafio_android_robson_lima.model.Hero;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HeroService {
    @GET("characters")
    Call<Hero> getHeroesList();

    @GET("characters/{heroId}/comics")
    Call<ComicBook> getComicBooks(@Path("heroId") int heroId);
}
