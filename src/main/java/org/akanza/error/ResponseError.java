package org.akanza.error;

/**
 * Created by AMANI on 10/08/2016.
 */
public class ResponseError
{
    private int code;
    private String message;
    private String description;

    public ResponseError()
    {}

    public int getCode()
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
