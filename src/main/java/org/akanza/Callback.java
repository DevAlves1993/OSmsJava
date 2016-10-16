package org.akanza;

import org.akanza.error.ResponseError;
import org.akanza.handler.OnFailure;
import org.akanza.handler.OnSuccess;
import org.akanza.handler.OnThrowable;
import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.BaseResponse;


/**
 * Created by Christian Amani on 10/08/2016.
 * <p>The class Callback manage the various events which intervene during the call of the SMS API.</p>
 */

public class Callback
{
    private OnSuccess success;
    private OnFailure failure;
    private OnThrowable throwable;

    /**
     * @param onSuccess
     * @param onFailure
     * @param onThrowable
     */
    public Callback(OnSuccess onSuccess,OnFailure onFailure,OnThrowable onThrowable)
    {
        this.success = onSuccess;
        this.failure = onFailure;
        this.throwable = onThrowable;
    }

    /**
     * @param onSuccess
     * @param onFailure
     */
    public Callback(OnSuccess onSuccess,OnFailure onFailure)
    {
        this.success = onSuccess;
        this.failure = onFailure;
        this.throwable = (Throwable::printStackTrace);
    }

    /**
     * @param onFailure
     * @param onThrowable
     */
    public Callback(OnFailure onFailure,OnThrowable onThrowable)
    {
        this.success = null;
        this.failure = onFailure;
        this.throwable = onThrowable;
    }

    /**
     * @param onSuccess
     */
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
        this.throwable = (Throwable::printStackTrace);
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
