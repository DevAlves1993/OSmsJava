package org.akanza.osms.core;

import org.akanza.osms.core.exception.HttpApiOrangeException;
import org.akanza.osms.model.OrangeSMS;
import org.akanza.osms.model.response.ContractsSMS;
import org.akanza.osms.model.response.HistoricPurchase;
import org.akanza.osms.model.response.ResponseSMS;
import org.akanza.osms.model.response.ResponseSubscription;
import org.akanza.osms.model.response.StatisticSMS;

import java.io.IOException;

import okhttp3.MediaType;


/**
 * Created by user on 05/02/2017.
 */

public interface HttpApiOrange
{
    String SCHEME = "http";
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
