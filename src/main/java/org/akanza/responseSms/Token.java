package org.akanza.responseSms;

/**
 * Created by Christian Amani on 24/10/2015.
 */


public class Token extends BaseResponse
{
    private String tokenType;
    private String accessToken;
    private long expiresIn;

    public Token(String tokenType, String accessToken, long expiresIn)
    {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getTokenType()
    {
        return tokenType;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public long getExpiresIn()
    {
        return expiresIn;
    }

    public String createAccess()
    {
        return tokenType +" "+ accessToken;
        // TODO: see a doc concerning Access
    }
}