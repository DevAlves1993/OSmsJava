package org.akanza;

import com.google.gson.*;
import okhttp3.*;
import org.akanza.error.ResponseError;
import org.akanza.error.ServiceException;
import org.akanza.models.ResponseHeader;
import org.akanza.responsesms.Token;
import static org.akanza.Resource.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by Christian Amani on 10/08/2016.
 * <p>FactoryToken it is factory which generate a Token of different way.</p>
 */
public class FactoryToken
{

    private static final Gson gson = new GsonBuilder()
            .create();


    /**
     * <p>Instantiate Token through Callback</p>
     * @param id Id Client
     * @param secretCode Secret Client
     * @param callback Callback which manages the events
     */
    public static void setToken(String id,String secretCode,Callback callback)
    {
        String basic = Credentials.basic(id, secretCode);

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
        Call call = HTTP_CLIENT.newCall(request);
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
                callback.success(token,responseHeader,i);
            }
            else
            {
                JsonParser jsonParser = new JsonParser();
                JsonElement element = jsonParser.parse(response.body().charStream());
                JsonObject jsonObject = element.getAsJsonObject();
                JsonObject jsonError = jsonObject.getAsJsonObject("error");
                ResponseError responseError = gson.fromJson(jsonError,ResponseError.class);
                String message = response.message();
                int i = response.code();
                callback.failure(responseError,message,i);
            }
        }
        catch (Exception e)
        {
            callback.throwable(e.getCause());
        }
        finally
        {
            if(response != null)
                response.close();
        }
    }

    /**
     * <p>Return  a simple token.</p>
     * @param id Id Client
     * @param secretCode Secret Client
     * @return Token
     * @throws IOException
     * @throws ServiceException
     */
    public static Token getToken(String id,String secretCode) throws ServiceException
    {
        String basic = Credentials.basic(id, secretCode);

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
        Call call = HTTP_CLIENT.newCall(request);
        try
        {
            response =  call.execute();
            if(response.isSuccessful())
                return gson.fromJson(response.body().charStream(), Token.class);
            else
            {
                ResponseError responseError = gson.fromJson(response.body().charStream(),ResponseError.class);
                throw new ServiceException(responseError);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(response != null)
                response.close();
        }
        return null;
    }


    /**
     * <p>Return a Token Future.</p>
     * @param id Id Client
     * @param secretCode Secret Client
     * @return Token Future
     * @throws IOException
     */
    public static Future<Token> getFutureToken(String id,String secretCode) throws IOException
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Token> future = executorService.submit(() -> executeFutureToken(id, secretCode));
        executorService.shutdown();
        return future;
    }


    private static Token executeFutureToken(String id, String secretCode) throws IOException,ServiceException
    {
        String basic = Credentials.basic(id, secretCode);

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
        Call call = HTTP_CLIENT.newCall(request);
        response = call.execute();
        if(response.isSuccessful())
            return gson.fromJson(response.body().charStream(),Token.class);
        ResponseError responseError = gson.fromJson(response.body().charStream(),ResponseError.class);
        throw new ServiceException(responseError);
    }

}
