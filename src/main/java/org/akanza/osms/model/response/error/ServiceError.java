package org.akanza.osms.model.response.error;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Christian Amani on 29/03/2017.
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
        String[] var = requestError
                .getException()
                .getVariables();
        return Arrays.asList(var);
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

        public static class ServiceException
        {
            private String messageId;
            private String text;
            private String[] variables;

            public ServiceException()
            {}

            public String getMessageId()
            {
                return messageId;
            }

            public String getText()
            {
                return text;
            }

            public String[] getVariables()
            {
                return variables;
            }
        }
    }
}