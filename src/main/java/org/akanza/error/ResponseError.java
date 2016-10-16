package org.akanza.error;

/**
 * Created by Christian Amani on 10/08/2016.
 * <p>Represent an error which can arise during calls it of the SMS API.</p>
 * @Author Amani Christian
 */
public class ResponseError
{
    /** Code error */
    private int code;
    /** Message error*/
    private String message;
    /** Description error */
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
