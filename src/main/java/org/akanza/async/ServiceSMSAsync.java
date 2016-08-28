package org.akanza.async;

import org.akanza.Callback;
import org.akanza.SMS;
import org.akanza.ServiceSMS;
import org.akanza.responseSms.*;

import java.util.concurrent.*;

/**
 * Created by user on 26/08/2016.
 */
public class ServiceSMSAsync
{
    private CompletionService<Object> completionService;
    private ExecutorService executor;
    private ServiceSMS serviceSMS;


    public ServiceSMSAsync()
    {
        executor = Executors.newCachedThreadPool();
        completionService = new ExecutorCompletionService<>(executor);
        serviceSMS = new ServiceSMS();
    }

    public void sendSMSAsync(Token token, SMS sms, Callback callback)
    {
        completionService.submit(() -> serviceSMS.sendSMS(token, sms, callback), 0);
    }

    public Future<ResponseSMS> sendSMSAsync(Token token,SMS sms)
    {
        //TODO : implement later
        return null;
    }

    public void sendSubscriptionAsync(Token token, Callback callback, String senderAddress)
    {
        completionService.submit(() -> serviceSMS.sendSubscription(token, callback, senderAddress), 0);
    }

    public Future<ResponseSubscription> sendSubscriptionAsync(Token token,String senderAddress)
    {
        // TODO : implement later
        return null;
    }

    public void obtainStatisticSMSAsync(Token token, Callback callback)
    {
        completionService.submit(() -> serviceSMS.obtainStatisticSMS(token, callback), 0);
    }

    public Future<StatisticSMS> obtainStatisticSMSAsync(Token token)
    {
        // TODO : implement later
        return null;
    }

    public void obtainsContractsSMSAsync(Token token, Callback callback)
    {
        completionService.submit(() -> serviceSMS.obtainsContractsSMS(token, callback), 0);
    }

    public Future<ContractsSMS> obtainsContractsSMSAsync(Token token)
    {
        // TODO : implement later
        return null;
    }

    public void obtainHistoricSMSAsync(Token token, Callback callback)
    {
        completionService.submit(() -> serviceSMS.obtainHistoricSMS(token, callback), 0);
    }

    public Future<HistoricPurchase> obtainHistoricSMSAsync(Token token)
    {
        // TODO : implement later
        return null;
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
}
