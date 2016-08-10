package org.akanza;

import org.akanza.error.ResponseError;
import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.BaseResponse;


/**
 * Created by AMANI on 10/08/2016.
 */

@FunctionalInterface
public interface Callback
{

    void onSuccess(BaseResponse baseResponse, ResponseHeader responseHeader, int statusCode);

    default void onFailure(ResponseError error, String message, int statusCode) {}

    default void onThrowable(Throwable throwable)
    {
        throwable.printStackTrace();
    }
}
