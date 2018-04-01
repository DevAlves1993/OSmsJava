package io.github.devalves.osms.model.error;

/**
 * Created by Christian Amani on 01/04/2018.
 */
public class RequestError {
    private ServiceException exception;
    public RequestError()
    {}

    public ServiceException getException() {
        return exception;
    }

    public void setException(ServiceException exception) {
        this.exception = exception;
    }
}