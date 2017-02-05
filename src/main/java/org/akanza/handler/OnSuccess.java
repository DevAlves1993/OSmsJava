package org.akanza.handler;

import org.akanza.models.ResponseHeader;
import org.akanza.responsesms.BaseResponse;

/**
 * Created by Christian Amani on 25/08/2016.
 *
 */
@FunctionalInterface
public interface OnSuccess
{
    /**
     * <p>This method runs when the call of the SMS API it is completed successfully.</p>
     * @param baseResponse
     * @param responseHeader Contains the header information of SMS API response
     * @param statusCode Status code of the SMS API response
     */
    void onSuccess(BaseResponse baseResponse, ResponseHeader responseHeader, int statusCode);
}
