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

    private final String END_POINT_REMAINDER = "https://api.orange.com/sms/admin/v1/contracts";
    private final String END_POINT_STATISTICS = "https://api.orange.com/sms/admin/v1/statistics";
    private final String END_POINT_HISTORIC = "https://api.orange.com/sms/admin/v1/purchaseorders";
    private final String AUTHORIZATION = "Authorization";

    private static final MediaType jsonMedia = MediaType.parse("application/json;charset=utf-8");;

    private  OkHttpClient httpClient;
    private Response response;
    private Gson gson;

    public ServiceSMS()
    {
        this.gson = new GsonBuilder()
                .create();
        this.httpClient = new OkHttpClient.Builder()
                .build();
    }

    private String encodedSenderAddress(String number)
    {
        return number.replaceAll(":\\+","%3A%2B");
    }
    private String createEndPointSms(String senderAddress)
    {
        String url = "https://api.orange.com/smsmessaging/v1/outbound/"+senderAddress+"/requests";
        return url;
    }

    public void sendSMS(Token token, SMS sms,Callback callback) throws ServiceException, IOException
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
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
                String message = response.message();
                int i = response.code();
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

    public StatisticSMS statisticSMS(Token token) throws IOException,ServiceException
    {
        StatisticSMS statisticSms = null;
        Request request = new Request.Builder()
                            .url(END_POINT_STATISTICS)
                            .addHeader(AUTHORIZATION,token.createAccess())
                            .build();
        Response response = httpClient.newCall(request).execute();
        if(response.isSuccessful())
        {
            statisticSms = gson.fromJson(response.body().charStream(),StatisticSMS.class);
            return statisticSms;
        }
        else
            throw new ServiceException(response.body().string());
    }
    public RemainderSMS remainderSMS(Token token) throws IOException,ServiceException
    {
        RemainderSMS remainderSms = null;
        Request request = new Request.Builder()
                            .url(END_POINT_REMAINDER)
                            .addHeader(AUTHORIZATION,token.createAccess())
                            .build();
        Response response = httpClient.newCall(request).execute();
        if(response.isSuccessful())
        {
            remainderSms = gson.fromJson(response.body().charStream(),RemainderSMS.class);
            return remainderSms;
        }
        else
            throw new ServiceException(response.body().string());
    }
    public HistoricPurchase obtainHistoric(Token token) throws IOException,ServiceException
    {
        HistoricPurchase historicpurchase = null;
        Request request = new Request.Builder()
                            .url(END_POINT_HISTORIC)
                            .addHeader(AUTHORIZATION,token.createAccess())
                            .build();
        Response response = httpClient.newCall(request).execute();
        if(response.isSuccessful())
        {
            historicpurchase = gson.fromJson(response.body().charStream(),HistoricPurchase.class);
            return historicpurchase;
        }
        else
            throw new ServiceException(response.body().string());
    }
}
