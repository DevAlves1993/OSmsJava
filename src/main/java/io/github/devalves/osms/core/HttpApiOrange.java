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
 *
 * <p>This interface includes all the methods for calling all the services provided by Orange API SMS</p>
 * <p>The principal implementation of this interface is the {@link OrangeSMS} class</p>
 *
 * @author Amani Christian Cyrille Alves
 */
public interface HttpApiOrange {

    String SCHEME = "https";
    String HOST = "api.orange.com";
    String VERSION_API = "v1";
    String HEADER_AUTHORISATION = "Authorization";
    MediaType JSON_MEDIA = MediaType.parse("application/json;charset=utf-8");

    /**
     * <p>Send an SMS</p>
     *
     * @param sms The SMS that will be sent. The OrangeSMS object contains all the information about the SMS
     * @return Returns the information concerning the sending of the SMS
     * @throws IOException
     * @throws HttpApiOrangeException
     */
    ResponseSMS sendSms(OrangeSMS sms) throws IOException,HttpApiOrangeException;

    /**
     * <p>Not use this method because in not implemented</p>
     *
     * @param senderAddress
     * @param subscription
     * @return
     * @throws IOException
     * @throws HttpApiOrangeException
     */
    ResponseSubscription subscriptionApi(String senderAddress,
            ResponseSubscription subscription) throws IOException,HttpApiOrangeException;

    /**
     * <p>Not use this method because in not implemented</p>
     * @param subId
     * @return
     * @throws IOException
     * @throws HttpApiOrangeException
     */
    ResponseSubscription checkSubscriptionApi(String subId) throws IOException,HttpApiOrangeException;

    /**
     * <p>Not use this method because in not implemented</p>
     * @param senderAddress
     * @param subId
     * @throws IOException
     * @throws HttpApiOrangeException
     */
    void unSubscriptionApi(String senderAddress, String subId) throws IOException,HttpApiOrangeException;

    /**
     * <p>Returns the static usage of the Orange API SMS service</p>
     *
     * @return Returns the static usage of the Orange API SMS service through the {@link StatisticSMS} object
     * @throws IOException
     * @throws HttpApiOrangeException
     */
    StatisticSMS obtainStatisticSMS() throws IOException,HttpApiOrangeException;

    /**
     * <p>Obtains the number of SMS messages remaining</p>
     *
     * @return Returns the number of SMS messages remaining through the {@link ContractsSMS} object
     * @throws IOException
     * @throws HttpApiOrangeException
     */
    ContractsSMS obtainsContractsSMS() throws IOException,HttpApiOrangeException;

    /**
     * <p>Obtains your purchase history.</p>
     *
     * @return Returns the history of your purchases through the {@link HistoricPurchase} object
     * @throws IOException
     * @throws HttpApiOrangeException
     */
    HistoricPurchase obtainHistoricSMS() throws IOException,HttpApiOrangeException;
}
