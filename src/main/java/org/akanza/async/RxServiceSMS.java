package org.akanza.async;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.akanza.SMS;
import org.akanza.responseSms.*;
import rx.Observable;



import java.io.IOException;

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

    public Observable<Object> responseSMSObservable(Token token,SMS sms)
    {
        // TODO :
        return null;
    }

    public Observable<ResponseSubscription> responseSubscriptionObservable(Token token,String senderAddress)
    {
        // TODO :
        return null;
    }

    public Observable<StatisticSMS> statisticSMSObservable(Token token)
    {
        // TODO :
        return null;
    }

    public Observable<ContractsSMS> contractsSMSObservable(Token token)
    {
        // TODO :
        return null;
    }

    public Observable<HistoricPurchase> historicPurchaseObservable(Token token)
    {
        // TODO :
        return null;
    }

    private Void createResponseResource(Token token,SMS sms) throws IOException
    {
        // TODO : using other library for the IO
        return null;
    }
}
