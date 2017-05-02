package org.akanza.osms.model.response.error;

import java.util.Arrays;
import java.util.List;

/**
 * @author Christian Amani
 */
public class ServiceError
{
    private RequestError requestError;

    public ServiceError()
    {}

    public String getMessage()
    {
        return requestError
                .getException()
                .getMessageId();
    }

    public String getText()
    {
        return requestError
                .getException()
                .getText();
    }

    public List<String> getVariables()
    {
        return requestError
                .getException()
                .getVariables();
    }

    public ServiceException getServiceException()
    {
        return requestError.getException();
    }


    public void setRequestError(RequestError requestError)
    {
        this.requestError = requestError;
    }

    public static class RequestError
    {
        private ServiceException exception;
        public RequestError()
        {}

        public ServiceException getException()
        {
            return exception;
        }

        public void setException(ServiceException exception)
        {
            this.exception = exception;
        }
    }
}
