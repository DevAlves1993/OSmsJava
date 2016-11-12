package org.akanza;


import com.google.gson.*;
import okhttp3.*;
import org.akanza.error.ResponseError;
import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.*;

import static org.akanza.Resource.*;

/**
 * Created by Christian Amani on 28/11/2015.
 * <p>The class ServiceSMS includes all the various interactions with the SMS API of Orange.</p>
 */
public class ServiceSMS
{

    private Gson gson;

    public ServiceSMS()
    {
        this.gson = new GsonBuilder()
                .create();
    }

    @Deprecated
    private String encodedSenderAddress(String number)
    {
        return number.replaceAll(":\\+","%3A%2B");
    }
    @Deprecated
    private String createEndPointSms(String senderAddress)
    {
        String url = "https://api.orange.com/smsmessaging/v1/outbound/"+senderAddress+"/requests";
        return url;
    }

    private void launchOnFailure(Callback callback, Response response)
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

    /**
     * <p>Send a SMS.</p>
     * @param token Your Token
     * @param sms Content of SMS elements
     * @param callback Callback which perform the actions
     */
    public void sendSMS(Token token, SMS sms,Callback callback)
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("smsmessaging")
                .addPathSegment("v1")
                .addPathSegment("outbound")
                .addEncodedPathSegment(sms.getOutBoundSMSMessageRequest().getSenderAddress())
                .addPathSegment("requests")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccessToken())
                .addHeader("Content-Type","application/json")
                .post(RequestBody.create(JSON_MEDIA,gson.toJson(sms)))
                .build();
        Response response = null;
        Call call = HTTP_CLIENT.newCall(request);
        try
        {
            response = call.execute();
            if(response.isSuccessful())
            {
                ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.date = response.header(DATE);
                responseHeader.contentType = response.header(CONTENT_TYPE);
                responseHeader.location = response.header(LOCATION);
                responseHeader.contentLength = response.header(CONTENT_LENGTH);
                ResponseSMS responseSMS = gson.fromJson(response.body().charStream(),ResponseSMS.class);
                int i = response.code();
                callback.success(responseSMS,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback, response);
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
     * @param token
     * @param callback
     * @param senderAddress
     */
    public void sendSubscription(Token token,Callback callback,String senderAddress)
    {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("smsmessaging")
                .addPathSegment("v1")
                .addPathSegment("outbound")
                .addPathSegment(senderAddress)
                .addPathSegment("subscriptions")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        Response response = null;
        Call call = HTTP_CLIENT.newCall(request);
        try
        {
            response = call.execute();
            if(response.isSuccessful())
            {
                ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.contentLength = response.header(CONTENT_LENGTH);
                responseHeader.contentType = response.header(CONTENT_TYPE);
                responseHeader.location = response.header(LOCATION);
                responseHeader.date = response.header(DATE);
                int i = response.code();
                ResponseSubscription responseSubscription = gson.fromJson(response.body().charStream(),ResponseSubscription.class);
                callback.success(responseSubscription,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback,response);
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
     * @param token
     * @param callback
     */
    public void obtainStatisticSMS(Token token, Callback callback)
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("statistics")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccessToken())
                .get()
                .build();
        Response response = null;
        Call call = HTTP_CLIENT.newCall(request);
        try
        {
            response = call.execute();
            if(response.isSuccessful())
            {
                ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.contentLength = response.header(CONTENT_LENGTH);
                responseHeader.location = response.header(LOCATION);
                responseHeader.contentType = response.header(CONTENT_TYPE);
                responseHeader.date = response.header(DATE);
                int i = response.code();
                StatisticSMS statisticSMS = gson.fromJson(response.body().charStream(),StatisticSMS.class);
                callback.success(statisticSMS,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback, response);
            }
        }
        catch(Exception e)
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
     * @param token
     * @param callback
     */
    public void obtainsContractsSMS(Token token, Callback callback)
    {
        HttpUrl url = new HttpUrl.Builder()
               .scheme(SCHEME)
               .host(HOST)
               .addPathSegment("sms")
               .addPathSegment("admin")
               .addPathSegment("v1")
               .addPathSegment("contracts")
               .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccessToken())
                .get()
                .build();
        Response response = null;
        Call call = HTTP_CLIENT.newCall(request);
        try
        {
            response = call.execute();
            if(response.isSuccessful())
            {
                ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.contentLength = response.header(CONTENT_LENGTH);
                responseHeader.contentType = response.header(CONTENT_TYPE);
                responseHeader.location = response.header(LOCATION);
                responseHeader.date = response.header(DATE);
                ContractsSMS contractsSMS = gson.fromJson(response.body().charStream(),ContractsSMS.class);
                int i = response.code();
                callback.success(contractsSMS,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback,response);
            }
        }
        catch(Exception e)
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
     * @param token
     * @param callback
     */
    public void obtainHistoricSMS(Token token,Callback callback)
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("purchaseorders")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccessToken())
                .get()
                .build();
        Response response = null;
        Call call = HTTP_CLIENT.newCall(request);
        try
        {
            response = call.execute();
            if(response.isSuccessful())
            {
                ResponseHeader responseHeader = new ResponseHeader();
                responseHeader.contentLength = response.header(CONTENT_LENGTH);
                responseHeader.contentType = response.header(CONTENT_TYPE);
                responseHeader.date = response.header(DATE);
                responseHeader.location = response.header(LOCATION);
                int i = response.code();
                HistoricPurchase historicPurchase = gson.fromJson(response.body().charStream(),HistoricPurchase.class);
                callback.success(historicPurchase,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback,response);
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
}
