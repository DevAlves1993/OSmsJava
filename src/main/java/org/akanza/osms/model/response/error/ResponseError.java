package org.akanza.osms.model.response.error;

/**
 * Created by Christian Amani on 10/08/2016.
 * <p>Represent an error which can arise during calls it of the SMS API.</p>
 * @Author Amani Christian
 */
public class ResponseError
{
    private Error error;

    public ResponseError()
    {}

    public String getCode()
    {
        return error.getCode();
    }

    public String getMessage()
    {
        return error.getMessage();
    }

    public String getDescription()
    {
        return error.getDescription();
    }

    public static class Error
    {
        /** Code error */
        private String code;
        /** Message error*/
        private String message;
        /** Description error */
        private String description;

        public Error()
        {}

        public String getCode()
        {
            return code;
        }

        public String getMessage()
        {
            return message;
        }

        public String getDescription()
        {
            return description;
        }
    }
}
