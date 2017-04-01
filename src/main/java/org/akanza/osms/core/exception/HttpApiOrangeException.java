package org.akanza.osms.core.exception;

import org.akanza.osms.model.response.error.ResponseError;
import org.akanza.osms.model.response.error.ServiceError;
import org.akanza.osms.model.response.error.ServiceException;

/**
 * @author Christian Amani
 */

public class HttpApiOrangeException extends RuntimeException
{
    private ResponseError responseError;
    private ServiceError serviceError;

    public HttpApiOrangeException(ResponseError responseError)
    {
        this.responseError = responseError;
        this.serviceError = null;
    }

    public HttpApiOrangeException(ServiceError serviceError)
    {
        this.serviceError = serviceError;
        this.responseError = null;
    }

    public ResponseError getResponseError()
    {
        return responseError;
    }

    @Override
    public String getMessage()
    {
        if(responseError != null)
            return responseError.getMessage();
        return serviceError.getMessage();
    }

    public ServiceException getServiceException()
    {
        return serviceError.getServiceException();
    }

    public boolean errorIsService()
    {
        return serviceError != null;
    }
}
