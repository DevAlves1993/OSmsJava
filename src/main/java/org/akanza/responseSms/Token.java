package org.akanza.responseSms;

/**
 * Created by Christian Amani on 24/10/2015.
 */


public class Token extends BaseResponse
{
    private String token_type;
    private String access_token;
    private long expires_in;

    public Token(String token_type, String access_token, long expires_in)
    {
        this.token_type = token_type;
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getToken_type()
    {
        return token_type;
    }

    public String getAccess_token()
    {
        return access_token;
    }

    public long getExpires_in()
    {
        return expires_in;
    }

    public String createAccess()
    {
        return token_type +" "+ access_token;
    }
}