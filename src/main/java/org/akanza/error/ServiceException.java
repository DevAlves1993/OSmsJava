package org.akanza.error;

/**
 * Created by Christian Amani on 28/11/2015.
 */
public class ServiceException extends Exception
{
    private ResponseError detailError;

    public ServiceException(String message)
    {
        super(message);
    }
    public ServiceException(ResponseError responseError)
    {
        super();
        this.detailError = responseError;
    }

    public ResponseError getDetailError()
    {
        return detailError;
    }
}
