package org.akanza.responsesms;

/**
 * Created by Christian Amani on 24/10/2015.
 * <p>Token it is object which your permit to access a API SMS.</p>
 * @Author Christian Amani
 */


public class Token extends BaseResponse
{
    /** The type of the token issued.*/
    private String tokenType;

    /**The access token issued by the authorization server, and to be used for your API calls,
     * by setting the header as follows: Authorization: Bearer {access_token}. */
    private String accessToken;
    /** The lifetime in seconds of the access token.*/
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