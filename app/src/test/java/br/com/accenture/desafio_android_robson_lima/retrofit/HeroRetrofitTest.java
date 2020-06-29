package br.com.accenture.desafio_android_robson_lima.retrofit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.accenture.desafio_android_robson_lima.retrofit.service.HeroService;

import static br.com.accenture.desafio_android_robson_lima.util.AppConstants.BASE_URL;

@RunWith(MockitoJUnitRunner.class)
public class HeroRetrofitTest {
    @InjectMocks
    HeroRetrofit heroRetrofit = new HeroRetrofit();
    HeroService heroService = heroRetrofit.getHeroService();


    @Test
    public void getServiceTest(){
        Assert.assertNotNull(heroRetrofit);
        Assert.assertNotNull(heroService);
    }

    @Test
    public void getBaseUrlTest(){
        Assert.assertEquals(BASE_URL,heroRetrofit.getHeroRetrofit().baseUrl().toString());
    }
}
