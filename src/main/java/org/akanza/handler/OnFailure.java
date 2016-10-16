package org.akanza.handler;

import org.akanza.error.ResponseError;

/**
 * Created by Christian Amani on 25/08/2016.
 */
@FunctionalInterface
public interface OnFailure
{
    /**
     * <p>This method runs when the call of the SMS API occurred with a error.</p>
     * @param error ResponseError
     * @param message Message which describe error
     * @param statusCode Error status code
     */
    void onFailure(ResponseError error, String message, int statusCode);
}
