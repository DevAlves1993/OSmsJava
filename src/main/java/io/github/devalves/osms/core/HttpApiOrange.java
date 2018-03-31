package io.github.devalves.osms.core;

import io.github.devalves.osms.core.exception.HttpApiOrangeException;
import io.github.devalves.osms.model.response.ResponseSMS;
import io.github.devalves.osms.model.OrangeSMS;
import io.github.devalves.osms.model.response.ContractsSMS;
import io.github.devalves.osms.model.response.HistoricPurchase;
import io.github.devalves.osms.model.response.ResponseSubscription;
import io.github.devalves.osms.model.response.StatisticSMS;

import java.io.IOException;

import okhttp3.MediaType;


/**
 * @author Christian Amani
 */

public interface HttpApiOrange {
    String SCHEME = "https";
    String HOST = "api.orange.com";
    String VERSION_API = "v1";
    String HEADER_AUTHORISATION = "Authorization";
    MediaType JSON_MEDIA = MediaType.parse("application/json;charset=utf-8");
    ResponseSMS sendSms(OrangeSMS sms) throws IOException,HttpApiOrangeException;
    ResponseSubscription subscriptionApi(String senderAddress,
            ResponseSubscription subscription) throws IOException,HttpApiOrangeException;
    ResponseSubscription checkSubscriptionApi(String subId) throws IOException,HttpApiOrangeException;
    void unSubscriptionApi(String senderAddress, String subId) throws IOException,HttpApiOrangeException;
    StatisticSMS obtainStatisticSMS() throws IOException,HttpApiOrangeException;
    ContractsSMS obtainsContractsSMS() throws IOException,HttpApiOrangeException;
    HistoricPurchase obtainHistoricSMS() throws IOException,HttpApiOrangeException;
}
