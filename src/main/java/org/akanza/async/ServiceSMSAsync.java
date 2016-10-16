package org.akanza.async;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.akanza.Callback;
import org.akanza.SMS;
import org.akanza.ServiceSMS;
import org.akanza.responseSms.*;

import java.io.IOException;
import java.util.concurrent.*;

import static org.akanza.Resource.*;

/**
 * Created by Christian Amani on 26/08/2016.
 * <p>The class ServiceSMSAsync it is version which implement a API asynchronous of JAVA 7 and and JAVA 8.</p>
 */
public class ServiceSMSAsync
{
    private CompletionService<Object> completionService;
    private ExecutorService executor;
    private ServiceSMS serviceSMS;
    private Gson gson;

    public ServiceSMSAsync()
    {
        executor = Executors.newCachedThreadPool();
        completionService = new ExecutorCompletionService<>(executor);
        serviceSMS = new ServiceSMS();
        gson = new GsonBuilder()
                .create();
    }

    public void sendSMSAsync(Token token, SMS sms, Callback callback)
    {
        completionService.submit(() -> serviceSMS.sendSMS(token, sms, callback), 0);
    }

    public Future<ResponseSMS> sendSMSAsync(Token token,SMS sms)
    {
         return executor.submit(() -> sendSMS(token,sms));
    }

    public void sendSubscriptionAsync(Token token, Callback callback, String senderAddress)
    {
        completionService.submit(() -> serviceSMS.sendSubscription(token, callback, senderAddress), 0);
    }

    public Future<ResponseSubscription> sendSubscriptionAsync(Token token,String senderAddress)
    {
        return executor.submit(() -> sendSubscription(token,senderAddress));
    }

    public void obtainStatisticSMSAsync(Token token, Callback callback)
    {
        completionService.submit(() -> serviceSMS.obtainStatisticSMS(token, callback), 0);
    }

    public Future<StatisticSMS> obtainStatisticSMSAsync(Token token)
    {
        return executor.submit(() -> obtainStatisticSMS(token));
    }

    public void obtainsContractsSMSAsync(Token token, Callback callback)
    {
        completionService.submit(() -> serviceSMS.obtainsContractsSMS(token, callback), 0);
    }

    public Future<ContractsSMS> obtainsContractsSMSAsync(Token token)
    {
        return executor.submit(() -> obtainsContractsSMS(token));
    }

    public void obtainHistoricSMSAsync(Token token, Callback callback)
    {
        completionService.submit(() -> serviceSMS.obtainHistoricSMS(token, callback), 0);
    }

    public Future<HistoricPurchase> obtainHistoricSMSAsync(Token token)
    {
        return executor.submit(() -> obtainHistoric(token));
    }

    public void shutdownServiceSMSAsync(long timeout, TimeUnit timeUnit) throws InterruptedException
    {
        ScheduledExecutorService executorStopTask = Executors.newSingleThreadScheduledExecutor();
        executorStopTask.schedule(() ->
                {
                    executor.shutdownNow();
                    executorStopTask.shutdown();
                }
                ,timeout,timeUnit);
    }

    public void shutdownServiceSMSAsync()
    {
        executor.shutdown();
    }

    public void shutdownNowServiceSMSAsync()
    {
        executor.shutdownNow();
    }

    private ResponseSMS sendSMS(Token token,SMS sms) throws IOException
    {
        if(token != null && sms != null)
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
                    .post(RequestBody.create(jsonMedia,gson.toJson(sms)))
                    .build();
            Response response;
            Call call = httpClient.newCall(request);
            response = call.execute();
            if(response.isSuccessful())
            {
                ResponseSMS responseSMS = gson.fromJson(response.body().charStream(), ResponseSMS.class);
                response.close();
                return responseSMS;
            }
        }
        return null;
    }

    private ResponseSubscription sendSubscription(Token token,String senderAddress) throws IOException
    {
        if(token != null && senderAddress !=  null)
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
            Response response;
            Call call = httpClient.newCall(request);
            response = call.execute();
            if(response.isSuccessful())
            {
                ResponseSubscription responseSubscription = gson.fromJson(response.body().charStream(), ResponseSubscription.class);
                response.close();
                return responseSubscription;
            }
        }
        return null;
    }

    private StatisticSMS obtainStatisticSMS(Token token) throws IOException
    {
        if(token != null)
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
            Response response;
            Call call = httpClient.newCall(request);
            response = call.execute();
            if(response.isSuccessful())
            {
                StatisticSMS statisticSMS = gson.fromJson(response.body().charStream(), StatisticSMS.class);
                response.close();
                return statisticSMS;
            }
        }
        return null;
    }

    public ContractsSMS obtainsContractsSMS(Token token) throws IOException
    {
        if(token != null)
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
            Response response;
            Call call = httpClient.newCall(request);
            response = call.execute();
            if(response.isSuccessful())
            {
                ContractsSMS contractsSMS = gson.fromJson(response.body().charStream(), ContractsSMS.class);
                response.close();
                return contractsSMS;
            }
        }
        return null;
    }

    public HistoricPurchase obtainHistoric(Token token) throws IOException
    {
        if(token != null)
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
            Response response;
            Call call = httpClient.newCall(request);
            response = call.execute();
            if(response.isSuccessful())
            {
                HistoricPurchase historicPurchase = gson.fromJson(response.body().charStream(), HistoricPurchase.class);
                response.close();
                return historicPurchase;
            }
        }
        return null;
    }
}