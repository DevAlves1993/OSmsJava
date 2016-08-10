package org.akanza;

import com.squareup.okhttp.Response;
import org.akanza.models.SMSHeader;
import org.akanza.responseSms.BaseResponse;

/**
 * Created by AMANI on 10/08/2016.
 */

@FunctionalInterface
public interface Callback
{

    void onSuccess(BaseResponse baseResponse, SMSHeader smsHeader,int statusCode);

    default void onFailure(Response errorResponse) {}

    default void onThrowable(Throwable throwable)
    {
        throwable.printStackTrace();
    }
}
