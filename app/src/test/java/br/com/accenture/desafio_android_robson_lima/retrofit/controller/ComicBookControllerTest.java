package br.com.accenture.desafio_android_robson_lima.retrofit.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ComicBookControllerTest {

    @InjectMocks
    ComicBookController controller = Mockito.mock(ComicBookController.class);

    @Test
    public void getComicBookListTest (){ Assert.assertNotNull(controller.getHeroComics(1009148)); }
}
