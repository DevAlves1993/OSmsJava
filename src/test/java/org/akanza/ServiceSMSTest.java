package org.akanza;

import org.akanza.responseSms.BaseResponse;
import org.akanza.responseSms.ResponseSMS;
import org.akanza.responseSms.Token;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Christian Amani on 12/09/2016.
 */
public class ServiceSMSTest
{
    final String id = "";
    final String secretCode = "";
    final String address = "";
    final String senderAddress = "";
    final String content = "";
    Token token = null;
    ServiceSMS serviceSMS;
    Callback callback;

    @Before
    public void initTest()
    {
        Callback callback = new Callback((b,r,i)->
            {
                System.out.println("The recuperation of token is passed with");
                token = (Token) b;
            }
        ,(e,m,i)->
            {
                System.out.println("The recuperation of token is faillure");
                System.out.println("The code error it is"+e.getCode());
                System.out.println("The message error it is"+e.getMessage());
                System.out.println("The Description error it is"+e.getDescription());
            }
        );
        this.callback = new Callback((b,h,s)->
        {
            System.out.println("The status code it is : "+s);
            System.out.println("The Lenght content it is : "+h.contentLength);
            System.out.println("The content Type it is : "+h.contentType);
            System.out.println("The Location it is  : "+h.location);
            System.out.println("The Date it is : "+h.date);
            // supply others operations on b (BaseResponse)
        });
        FactoryService.setToken(id,secretCode,callback);
        serviceSMS = new ServiceSMS();
    }

    @Test
    public void sendSMS() throws Exception
    {
        SMS sms = new SMS(address,senderAddress,content);
        serviceSMS.sendSMS(token,sms,callback);
    }

    @Test
    public void sendSubscription() throws Exception
    {
        String senderAdrress = "";
        serviceSMS.sendSubscription(token,callback,senderAdrress);
    }

    @Test
    public void obtainStatisticSMS() throws Exception
    {
        serviceSMS.obtainStatisticSMS(token,callback);
    }

    @Test
    public void obtainsContractsSMS() throws Exception
    {
        serviceSMS.obtainsContractsSMS(token,callback);
    }

    @Test
    public void obtainHistoricSMS() throws Exception
    {
        serviceSMS.obtainHistoricSMS(token,callback);
    }

}