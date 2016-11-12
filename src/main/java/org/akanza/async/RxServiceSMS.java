package org.akanza.async;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.akanza.SMS;
import org.akanza.error.ResponseError;
import org.akanza.error.ServiceException;
import org.akanza.responseSms.*;
import org.akanza.Callback;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


import java.io.IOException;

import static org.akanza.Resource.*;

/**
 * Created by Christian Amani on 27/08/2016.
 * <p>The class RxServiceSMS it is version Rx of Class ServiceSMS.</p>
 */
public class RxServiceSMS
{

    private Gson gson;

    public RxServiceSMS()
    {
        gson = new GsonBuilder()
                .create();
    }

    /**
     * <p>Send a SMS</p>
     * @param token
     * @param sms
     * @param callback
     * @return Observable
     */
    public Observable<ResponseSMS> responseSMSObservable(Token token,SMS sms,Callback callback)
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
        Call call = HTTP_CLIENT.newCall(request);
        return Observable.just(call)
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Call, Observable<ResponseSMS>>()
                {
                    @Override
                    public Observable<ResponseSMS> call(Call call)
                    {
                        Response response = null;
                        try
                        {
                            response = call.execute();
                            if(response.isSuccessful())
                            {
                                ResponseSMS responseSMS = gson.fromJson(response.body().charStream(),ResponseSMS.class);
                                return Observable.just(responseSMS);
                            }
                            else
                            {
                                ResponseError responseError = gson.fromJson(response.body().charStream(),ResponseError.class);
                                throw new ServiceException(responseError);
                            }
                        }
                        catch (IOException | ServiceException e)
                        {
                            return Observable.error(e);
                        }
                        finally
                        {
                            if(response != null)
                                response.close();
                        }
                    }
                })
                .doOnError(throwable -> manageError(throwable, callback));
    }

    /**
     * <p>Subscribe to SMS delivery receipt notification</p>
     * @param token
     * @param senderAddress
     * @param callback
     * @return Observable
     */
    public Observable<ResponseSubscription> responseSubscriptionObservable(Token token,String senderAddress,Callback callback)
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
        Call call = HTTP_CLIENT.newCall(request);
        return Observable.just(call)
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Call, Observable<ResponseSubscription>>()
                {
                    @Override
                    public Observable<ResponseSubscription> call(Call call)
                    {
                        Response response = null;
                        try
                        {
                            response = call.execute();
                            if(response.isSuccessful())
                            {
                                ResponseSubscription responseSubscription = gson.fromJson(response.body().charStream()
                                        ,ResponseSubscription.class);
                                return Observable.just(responseSubscription);
                            }
                            else
                            {
                                ResponseError responseError = gson.fromJson(response.body().charStream(),ResponseError.class);
                                throw new ServiceException(responseError);
                            }
                        }
                        catch(IOException | ServiceException e)
                        {
                            return Observable.error(e);
                        }
                        finally
                        {
                            if(response != null)
                                response.close();
                        }
                    }
                })
                .doOnError(throwable -> manageError(throwable, callback));
    }

    /**
     * <p>Obtain usage statistics of your account SMS API.</p>
     * @param token
     * @param callback
     * @return Observable
     */
    public Observable<StatisticSMS> statisticSMSObservable(Token token,Callback callback)
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
        Call call = HTTP_CLIENT.newCall(request);
        return Observable.just(call)
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Call, Observable<StatisticSMS>>()
                {
                    @Override
                    public Observable<StatisticSMS> call(Call call)
                    {
                        Response response = null;
                        try
                        {
                            response = call.execute();
                            if(response.isSuccessful())
                            {
                                StatisticSMS statisticSMS = gson.fromJson(response.body().charStream(),StatisticSMS.class);
                                return Observable.just(statisticSMS);
                            }
                            else
                            {
                                ResponseError responseError = gson.fromJson(response.body().charStream(),ResponseError.class);
                                throw new ServiceException(responseError);
                            }
                        }
                        catch (IOException | ServiceException e)
                        {
                            return Observable.error(e);
                        }
                        finally
                        {
                            if(response != null)
                                response.close();
                        }
                    }
                })
                .doOnError(throwable -> manageError(throwable,callback));
    }

    /**
     * <p>Obtains the remaining amount of SMS and the others information.</p>
     * @param token
     * @param callback
     * @return Observable
     */
    public Observable<ContractsSMS> contractsSMSObservable(Token token,Callback callback)
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
        Call call = HTTP_CLIENT.newCall(request);
        return Observable.just(call)
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Call, Observable<ContractsSMS>>()
                {
                    @Override
                    public Observable<ContractsSMS> call(Call call)
                    {
                        Response response = null;
                        try
                        {
                            response = call.execute();
                            if(response.isSuccessful())
                            {
                                ContractsSMS contractsSMS = gson.fromJson(response.body().charStream(),ContractsSMS.class);
                                return Observable.just(contractsSMS);
                            }
                            else
                            {
                                ResponseError responseError = gson.fromJson(response.body().charStream(),ResponseError.class);
                                throw new ServiceException(responseError);
                            }
                        }
                        catch (IOException | ServiceException e)
                        {
                            return Observable.error(e);
                        }
                        finally
                        {
                            if(response != null)
                                response.close();
                        }
                    }
                })
                .doOnError(throwable -> manageError(throwable,callback));
    }

    /**
     * <p>Obtain purchase history associated with your account.</p>
     * @param token
     * @param callback
     * @return a Observable
     */
    public Observable<HistoricPurchase> historicPurchaseObservable(Token token,Callback callback)
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
        Call call = HTTP_CLIENT.newCall(request);
        return Observable.just(call)
                .observeOn(Schedulers.io())
                .flatMap(new Func1<Call, Observable<HistoricPurchase>>()
                {
                    @Override
                    public Observable<HistoricPurchase> call(Call call)
                    {
                        Response response = null;
                        try
                        {
                            response = call.execute();
                            if(response.isSuccessful())
                            {
                                HistoricPurchase historicPurchase = gson.fromJson(response.body().charStream(),HistoricPurchase.class);
                                return Observable.just(historicPurchase);
                            }
                            else
                            {
                                ResponseError responseError = gson.fromJson(response.body().charStream(),ResponseError.class);
                                throw new ServiceException(responseError);
                            }
                        }
                        catch (IOException | ServiceException e)
                        {
                            return Observable.error(e);
                        }
                        finally
                        {
                            if(response != null)
                                response.close();
                        }
                    }
                })
                .doOnError(throwable -> manageError(throwable,callback));
    }

    private void manageError(Throwable throwable, Callback callback)
    {
        if(throwable instanceof IOException)
            callback.throwable(throwable);
        else if(throwable instanceof ServiceException)
        {
            ResponseError detailError = ((ServiceException) throwable).getResponseError();
            String message = detailError.getMessage();
            int statusCode = detailError.getCode();
            callback.failure(detailError,message,statusCode);
        }
    }
}
