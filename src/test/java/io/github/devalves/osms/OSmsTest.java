package io.github.devalves.osms;

import io.github.devalves.osms.core.CountryCode;
import io.github.devalves.osms.core.exception.HttpApiOrangeException;
import io.github.devalves.osms.model.*;
import io.github.devalves.osms.model.historic.PurchaseOrder;
import io.github.devalves.osms.model.partnercontract.Contract;
import io.github.devalves.osms.model.partnercontract.PartnerContracts;
import io.github.devalves.osms.model.sms.OrangeSMS;
import io.github.devalves.osms.model.sms.ResponseSMS;
import io.github.devalves.osms.model.sms.SMSResponse;
import io.github.devalves.osms.model.statistic.PartnerStatistics;
import okhttp3.OkHttpClient;
import io.github.devalves.osms.model.partnercontract.ContractsSMS;
import io.github.devalves.osms.model.historic.HistoricPurchase;
import io.github.devalves.osms.model.statistic.StatisticSMS;
import io.github.devalves.osms.model.error.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author Amani Christian Cyrille Alves
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
                .id("Your Id")
                .secretCode("Your Secret Code")
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
        Contract[] contracts = partnerContracts.getContracts();
        Arrays.asList(contracts)
                .forEach(this::printContracts);
    }

    private void printContracts(Contract contract)
    {
        String contractDescription = contract.getContractDescription();
        System.out.println(contractDescription);
        String service = contract.getService();
        System.out.println(service);
    }

    private void printResponseSMS(ResponseSMS responseSMS)
    {
        SMSResponse response = responseSMS.getOutBoundSMSMessageRequest();
        String address = response.getAddress();
        System.out.println(address);
        String senderAddress = response.getSenderAddress();
        System.out.println(senderAddress);
        String senderName = response.getSenderName();
        System.out.println(senderName);
    }

}