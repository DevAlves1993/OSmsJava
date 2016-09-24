package org.akanza;

import okhttp3.*;
import okhttp3.internal.framed.Header;
import org.akanza.error.ResponseError;
import org.akanza.handler.OnFailure;
import org.akanza.handler.OnSuccess;
import org.akanza.handler.OnThrowable;
import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.BaseResponse;
import org.akanza.responseSms.Token;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AMANI on 10/08/2016.
 */
public class FactoryServiceTest
{
    final String id = "";
    final String secretCode = "";
    Token token;
    final Callback callback = new Callback(new OnSuccess()
    {
        @Override
        public void onSuccess(BaseResponse baseResponse, ResponseHeader responseHeader, int statusCode)
        {
            // Print Header information
            System.out.println("The status code it is : "+statusCode);
            System.out.println("The Lenght content it is : "+responseHeader.contentLength);
            System.out.println("The content Type it is : "+responseHeader.contentType);
            System.out.println("The Location it is  : "+responseHeader.location);
            System.out.println("The Date it is : "+responseHeader.date);
            // init token
            token = (Token) baseResponse;
        }
    }, new OnFailure()
    {
        @Override
        public void onFailure(ResponseError error, String message, int statusCode)
        {

            System.out.println("The recuperation of token is faillure");
            System.out.println("The code error it is"+error.getCode());
            System.out.println("The message error it is"+error.getMessage());
            System.out.println("The Description error it is"+error.getDescription());
        }
    }, new OnThrowable()
    {
        @Override
        public void onThrowable(Throwable throwable)
        {
            throwable.printStackTrace();
        }
    });

    @Test
    public void setToken() throws Exception
    {
        FactoryService.setToken(id,secretCode,callback);
        String access_token = token.getAccess_token();
        long expires_in = token.getExpires_in();
        String create_access = token.createAccess();
    }
}