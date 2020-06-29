package br.com.accenture.desafio_android_robson_lima.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UtilTest {
    @InjectMocks
    Util util = Mockito.mock(Util.class);

    @Test
    public void getDescriptionTest(){
        Assert.assertEquals(AppConstants.NO_DESCRIPTION, util.getDescription(""));
        Assert.assertEquals("Descrição", util.getDescription("Descrição"));
    }

    @Test
    public void getApiErrorTest(){
        Assert.assertEquals(AppConstants.BAD_REQUEST, Util.getApiError(400));
        Assert.assertEquals(AppConstants.UNAUTHORIZED, Util.getApiError(401));
        Assert.assertEquals(AppConstants.FORBIDDEN, Util.getApiError(403));
        Assert.assertEquals(AppConstants.NOT_FOUND, Util.getApiError(404));
        Assert.assertEquals(AppConstants.CONFLICT, Util.getApiError(409));
        Assert.assertEquals(AppConstants.BAD_GATEWAY, Util.getApiError(502));
        Assert.assertEquals(AppConstants.GATEWAY_TIMEOUT, Util.getApiError(504));
        Assert.assertEquals(AppConstants.UNKNOW_ERROR, Util.getApiError(0));
    }
}
