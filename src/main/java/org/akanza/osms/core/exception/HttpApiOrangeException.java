package org.akanza.osms.core.exception;

import org.akanza.osms.model.response.error.ResponseError;

/**
 * Created by user on 05/02/2017.
 */

public class HttpApiOrangeException extends RuntimeException
{
    private ResponseError error;

    public HttpApiOrangeException(ResponseError error)
    {
        this.error = error;
    }

    public ResponseError getError()
    {
        return error;
    }

    @Override
    public String getMessage()
    {
        return error.getMessage();
    }
}
