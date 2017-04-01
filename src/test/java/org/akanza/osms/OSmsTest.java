package org.akanza.osms;

import okhttp3.OkHttpClient;
import org.akanza.osms.core.CountryCode;
import org.akanza.osms.core.exception.HttpApiOAuthOrange;
import org.akanza.osms.core.exception.HttpApiOrangeException;
import org.akanza.osms.model.*;
import org.akanza.osms.model.response.ContractsSMS;
import org.akanza.osms.model.response.HistoricPurchase;
import org.akanza.osms.model.response.ResponseSMS;
import org.akanza.osms.model.response.StatisticSMS;
import org.akanza.osms.model.response.error.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Christian Amani on 29/03/2017.
 */
public class OSmsTest
{
    private OSms oSms;

    @Before
    public void setup() throws IOException
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        oSms = new OSms.BuilderOSms()
                .id("wcyuIqWa3Wj9S8kAZf9ALlHMZmVm34YL")
                .secretCode("8FCqRkFbPm6uiENY")
                .okHttpClient(client)
                .build();
        Token token = oSms.getToken();
        assertNotNull(token);
        System.out.println(token.getAccessToken());
        System.out.println(token.createAccess());
        System.out.println(token.getExpiresIn());
        System.out.println(token.getTokenType());
    }

    @Test
    public void sendSms()
    {
        OrangeSMS orangeSMS = new OrangeSMS("07149999","89349055","Sms test Orange ci", CountryCode.ivoryCoast);
        String address = orangeSMS.getMessage().getAddress();
        System.out.println(address);
        String senderAddress = orangeSMS.getMessage().getSenderAddress();
        System.out.println(senderAddress);
        ResponseSMS responseSMS = null;
        try
        {
            responseSMS = oSms.sendSms(orangeSMS);
        }
        catch(HttpApiOrangeException | IOException e)
        {
            e.printStackTrace();
            if(e instanceof HttpApiOrangeException)
            {
                if(((HttpApiOrangeException) e).errorIsService())
                {
                    ServiceException serviceException = ((HttpApiOrangeException) e).getServiceException();
                    printServiceException(serviceException);
                }
                else
                {
                    String message = e.getMessage();
                    System.out.println(message);
                }
            }
        }
        assertNotNull(responseSMS);
        printResponseSMS(responseSMS);
    }

    private void printServiceException(ServiceException serviceException)
    {
        String messageId = serviceException.getMessageId();
        System.out.println("Error Message Id : "+messageId);
        String text = serviceException.getText();
        System.out.println("Text is : "+text);
        List<String> variables = serviceException.getVariables();
        variables.forEach(System.out::println);
    }

    @Test
    public void checkSubscriptionApi() throws Exception
    {
        // TODO : implement later
    }

    @Test
    public void obtainStatisticSMS() throws Exception
    {
        StatisticSMS statisticSMS = oSms.obtainStatisticSMS();
        assertNotNull(statisticSMS);
        PartnerStatistics partnerStatistics = statisticSMS.getPartnerStatistics();
        assertNotNull(partnerStatistics);
        printPartnerStatistic(partnerStatistics);
    }

    @Test
    public void obtainsContractsSMS() throws Exception
    {
        ContractsSMS contractsSMS = oSms.obtainsContractsSMS();
        assertNotNull(contractsSMS);
        PartnerContracts partnerContracts = contractsSMS.getPartnerContracts();
        assertNotNull(partnerContracts);
        printPartnerContract(partnerContracts);
    }

    @Test
    public void obtainHistoricSMS() throws Exception
    {
        HistoricPurchase historicPurchase = oSms.obtainHistoricSMS();
        assertNotNull(historicPurchase);
        PurchaseOrder[] purchaseOrders = historicPurchase.getPurchaseOrders();
        Arrays.asList(purchaseOrders)
                .forEach(this::printPurchaseOrder);
    }

    private void printPurchaseOrder(PurchaseOrder purchaseOrder)
    {
        String bundleDescription = purchaseOrder.getBundleDescription();
        System.out.println(bundleDescription);
        String bundleId = purchaseOrder.getBundleId();
        System.out.println(bundleId);
        String mode = purchaseOrder.getMode();
        System.out.println(mode);
    }

    private void printPartnerStatistic(PartnerStatistics partnerStatistics)
    {
        String partnerId = partnerStatistics.getPartnerId();
        System.out.println(partnerId);
        PartnerStatistics.Statistic[] statistics = partnerStatistics.getStatistics();
        Arrays.asList(statistics)
            .forEach(this::printStatistic);
    }

    private void printStatistic(PartnerStatistics.Statistic statistic )
    {
        String service = statistic.getService();
        System.out.println(service);
    }

    private void printPartnerContract(PartnerContracts partnerContracts)
    {
        PartnerContracts.Contract[] contracts = partnerContracts.getContracts();
        Arrays.asList(contracts)
                .forEach(this::printContracts);
    }

    private void printContracts(PartnerContracts.Contract contract)
    {
        String contractDescription = contract.getContractDescription();
        System.out.println(contractDescription);
        String service = contract.getService();
        System.out.println(service);
    }

    private void printResponseSMS(ResponseSMS responseSMS)
    {
        ResponseSMS.SMSResponse response = responseSMS.getOutBoundSMSMessageRequest();
        String address = response.getAddress();
        System.out.println(address);
        String senderAddress = response.getSenderAddress();
        System.out.println(senderAddress);
        String senderName = response.getSenderName();
        System.out.println(senderName);
    }

}