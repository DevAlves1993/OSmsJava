package io.github.devalves.osms.model.error;

import java.util.List;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class ServiceError
{
    private RequestError requestError;

    public ServiceError()
    {}

    public String getMessage() {
        return requestError
                .getException()
                .getMessageId();
    }

    public String getText() {
        return requestError
                .getException()
                .getText();
    }

    public List<String> getVariables() {
        return requestError
                .getException()
                .getVariables();
    }

    public ServiceException getServiceException() {
        return requestError.getException();
    }


    public void setRequestError(RequestError requestError) {
        this.requestError = requestError;
    }
}
