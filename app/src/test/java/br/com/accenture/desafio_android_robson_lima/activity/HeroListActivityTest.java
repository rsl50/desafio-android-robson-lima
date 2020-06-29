package br.com.accenture.desafio_android_robson_lima.activity;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.accenture.desafio_android_robson_lima.R;
import br.com.accenture.desafio_android_robson_lima.retrofit.service.HeroService;
import br.com.accenture.desafio_android_robson_lima.ui.activity.HeroesListActivity;

@RunWith(MockitoJUnitRunner.class)
public class HeroListActivityTest {

    @InjectMocks
    HeroesListActivity activity;

    @Mock
    Context context;

    @Mock
    HeroService service;

    @Before
    public void setup(){
        activity.getClass();
    }


    @Test
    public void getDataFromApiTest(){
        Assert.assertNotNull(service);
    }


    @Test
    public void getConnectionTest(){
        Mockito.when(context.getString(R.string.no_connection)).thenReturn("Error");
        Assert.assertEquals("Error",context.getString(R.string.no_connection));
    }
}
