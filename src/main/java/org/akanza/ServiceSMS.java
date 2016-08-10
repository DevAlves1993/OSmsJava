package org.akanza;
/**
 * Created by AMANI on 28/11/2015.
 */

import java.io.IOException;

import com.google.gson.GsonBuilder;
import okhttp3.*;
import com.google.gson.Gson;
import org.akanza.error.ServiceException;
import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.*;

public class ServiceSMS
{
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LOCATION = "Location";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String DATE = "Date";

    private static final MediaType jsonMedia = MediaType.parse("application/json;charset=utf-8");;

    private  OkHttpClient httpClient;
    private Gson gson;

    public ServiceSMS()
    {
        this.gson = new GsonBuilder()
                .create();
        this.httpClient = new OkHttpClient.Builder()
                .build();
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
        String message = response.message();
        int i = response.code();
        callback.onFailure(message,i);
    }

    public void sendSMS(Token token, SMS sms,Callback callback) throws ServiceException, IOException
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("api.orange.com")
                .addPathSegment("smsmessaging")
                .addPathSegment("v1")
                .addPathSegment("outbound")
                .addEncodedPathSegment(sms.getOutBoundSMSMessageRequest().getSenderAddress())
                .addPathSegment("requests")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccess_token())
                .addHeader("Content-Type","application/json")
                .post(RequestBody.create(jsonMedia,gson.toJson(sms)))
                .build();
        Response response = null;
        Call call = httpClient.newCall(request);
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
                callback.onSuccess(responseSMS,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback, response);
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

    public void ObtainStatisticSMS(Token token, Callback callback)
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("api.orange.com")
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("statistics")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccess_token())
                .get()
                .build();
        Response response = null;
        Call call = httpClient.newCall(request);
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
                callback.onSuccess(statisticSMS,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback, response);
            }
        }
        catch(Exception e)
        {
            callback.onThrowable(e.getCause());
        }
        finally
        {
            if(response != null)
                response.close();
        }
    }

    public void obtainsContractsSMS(Token token, Callback callback)
    {
        HttpUrl url = new HttpUrl.Builder()
               .scheme("http")
               .host("api.orange.com")
               .addPathSegment("sms")
               .addPathSegment("admin")
               .addPathSegment("v1")
               .addPathSegment("contracts")
               .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccess_token())
                .get()
                .build();
        Response response = null;
        Call call = httpClient.newCall(request);
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
                callback.onSuccess(contractsSMS,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback,response);
            }
        }
        catch(Exception e)
        {
            callback.onThrowable(e.getCause());
        }
        finally
        {
            if(response != null)
                response.close();
        }
    }
    public void obtainHistoricSMS(Token token,Callback callback) throws IOException,ServiceException
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("api.orange.com")
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("purchaseorders")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization",token.getAccess_token())
                .get()
                .build();
        Response response = null;
        Call call = httpClient.newCall(request);
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
                callback.onSuccess(historicPurchase,responseHeader,i);
            }
            else
            {
                launchOnFailure(callback,response);
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
