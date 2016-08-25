package org.akanza.handler;

import org.akanza.error.ResponseError;

/**
 * Created by user on 25/08/2016.
 */
@FunctionalInterface
public interface OnFailure
{
    void onFailure(ResponseError error, String message, int statusCode);
}
