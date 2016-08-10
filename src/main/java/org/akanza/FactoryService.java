package org.akanza;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.Token;

/**
 * Created by AMANI on 10/08/2016.
 */
public class FactoryService
{

    private static final Gson gson = new GsonBuilder()
            .create();

    // TODO : effectuate a research on RequestBody
    public static void getToken(String id,String secretCode,Callback callback)
    {
        String basic = Credentials.basic(id, secretCode);
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
                .addHeader("Authorization",basic)
                .post(formBody)
                .build();
        Response response = null;
        Call call = httpClient.newCall(request);
        try
        {
            response =  call.execute();
            if(response.isSuccessful())
            {
                Token token = gson.fromJson(response.body().charStream(),Token.class);
                ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.contentLength = response.header("Content-Length");
                responseHeader.contentType = response.header("Content-Type");
                responseHeader.location = response.header("Location");
                responseHeader.date = response.header("Date");
                int i = response.code();
                callback.onSuccess(token,responseHeader,i);
            }
            else
            {
                int i = response.code();
                String message = response.message();
                callback.onFailure(message,i);
            }
        }
        catch (Exception e)
        {
            callback.onThrowable(e.getCause());
        }
        finally
        {
            if(response != null)
                response.close();
        }
    }
}
