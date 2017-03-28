package org.akanza.osms.core;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;

import org.akanza.osms.core.exception.HttpApiOrangeException;
import org.akanza.osms.model.Token;
import org.akanza.osms.model.response.ResponseError;

import java.io.IOException;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 02/02/2017.
 */

public abstract class Builder
{
    protected static OkHttpClient client = initHttpClient();

    protected String id;
    protected String secretCode;

    public Builder id(String id)
    {
        this.id = id;
        return this;
    }

    public Builder secretCode(String secretCode)
    {
        this.secretCode = secretCode;
        return this;
    }

    public Builder okHttpClient(OkHttpClient client)
    {
        Builder.client = null;
        Builder.client = client;
        return this;
    }

    protected Token obtainsToken() throws IOException, HttpApiOrangeException
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
        Response response;
        Call call = client.newCall(request);
        response = call.execute();
        if(response.isSuccessful())
        {
            return jsonToToken(response);
        }
        else
        {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    private ResponseError jsonToResponseError(Response response) throws IOException
    {
        Moshi moshi = new Moshi.Builder()
                .build();
        JsonAdapter<ResponseError> adapter = moshi.adapter(ResponseError.class);
        JsonReader reader = JsonReader.of(response.body().source());
        return adapter.fromJson(reader);
    }

    private Token jsonToToken(Response response) throws IOException
    {
        Moshi moshi = new Moshi.Builder()
                .build();
        JsonAdapter<Token> adapter = moshi.adapter(Token.class);
        JsonReader reader = JsonReader.of(response.body().source());
        return adapter.fromJson(reader);
    }

    private static OkHttpClient initHttpClient()
    {
        return new OkHttpClient.Builder()
                .dispatcher(new Dispatcher(Executors.newSingleThreadExecutor()))
                .build();
    }

    public abstract HttpApiOrange build() throws IOException, HttpApiOrangeException;

}
