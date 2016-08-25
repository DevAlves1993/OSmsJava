package org.akanza;

import org.akanza.error.ResponseError;
import org.akanza.handler.OnFailure;
import org.akanza.handler.OnSuccess;
import org.akanza.handler.OnThrowable;
import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.BaseResponse;


/**
 * Created by AMANI on 10/08/2016.
 */

public class Callback
{
    private OnSuccess success;
    private OnFailure failure;
    private OnThrowable throwable;

    public Callback(OnSuccess onSuccess,OnFailure onFailure,OnThrowable onThrowable)
    {
        this.success = onSuccess;
        this.failure = onFailure;
        this.throwable = onThrowable;
    }

    public Callback(OnSuccess onSuccess,OnFailure onFailure)
    {
        this.success = onSuccess;
        this.failure = onFailure;
        this.throwable = (e -> e.printStackTrace());
    }

    public Callback(OnSuccess onSuccess)
    {
        this.success = onSuccess;
        this.failure = ((e,s,i)
            ->
            {
                System.out.println("Status code it is : "+e.getCode());
                System.out.println("Message it is : "+e.getMessage());
                System.out.println("Description it is : "+e.getDescription());
                System.out.println("The response Error : "+s);
                System.out.println("The response code : "+i);
            }
        );
        this.throwable = (e -> e.printStackTrace());
    }

    public void success(BaseResponse baseResponse,ResponseHeader responseHeader,int statusCode)
    {
        success.onSuccess(baseResponse,responseHeader,statusCode);
    }

    public void failure(ResponseError responseError,String message, int statusCode)
    {
        failure.onFailure(responseError,message,statusCode);
    }

    public void throwable(Throwable t)
    {
        throwable.onThrowable(t);
    }
}
