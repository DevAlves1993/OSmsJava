package org.akanza;

import okhttp3.*;
import okhttp3.internal.framed.Header;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AMANI on 10/08/2016.
 */
public class FactoryServiceTest
{
    @Test
    public void getToken() throws Exception
    {

    }

    @Test
    public void testRequest() throws Exception
    {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.orange.com")
                .addPathSegment("oauth")
                .addPathSegment("v2")
                .addPathSegment("token")
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("grant_type","client_credentials")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization","") // TODO : See Doc [ encoded Authorisation  = id + secretCode
                .post(formBody)
                .build();
    }

}