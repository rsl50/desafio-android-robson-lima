package br.com.accenture.desafio_android_robson_lima.retrofit.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HeroControllerTest {

    @InjectMocks
    HeroController controller = Mockito.mock(HeroController.class);

    @Test
    public void getHeroesTest (){ Assert.assertNotNull(controller.getHeroes()); }
}
