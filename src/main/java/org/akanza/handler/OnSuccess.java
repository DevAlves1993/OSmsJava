package org.akanza.handler;

import org.akanza.models.ResponseHeader;
import org.akanza.responseSms.BaseResponse;

/**
 * Created by Christian Amani on 25/08/2016.
 */
@FunctionalInterface
public interface OnSuccess
{
    void onSuccess(BaseResponse baseResponse, ResponseHeader responseHeader, int statusCode);
}
