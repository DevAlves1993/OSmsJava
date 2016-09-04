package org.akanza.async;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.akanza.SMS;
import org.akanza.responseSms.ResponseSMS;
import org.akanza.responseSms.Token;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


import static org.akanza.Resource.*;

/**
 * Created by Christian Amani on 27/08/2016.
 */
public class RxServiceSMS
{

    private Gson gson;

    public RxServiceSMS()
    {
        gson = new GsonBuilder()
                .create();
    }

    public Observable<ResponseSMS> ResponseSMSObservable(Token token,SMS sms)
    {
        // TODO : using other library for the IO
        return Observable.using(new Func0<OkHttpClient>()
        {
            @Override
            public OkHttpClient call()
            {
                return null;
            }
        }, new Func1<OkHttpClient, Observable<ResponseSMS>>()
        {
            @Override
            public Observable<ResponseSMS> call(OkHttpClient okHttpClient)
            {
                return null;
            }
        }, new Action1<OkHttpClient>()
        {
            @Override
            public void call(OkHttpClient okHttpClient)
            {

            }
        })
        .subscribeOn(Schedulers.io());
    }
}
