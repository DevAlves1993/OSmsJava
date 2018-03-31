package io.github.devalves.osms.model;

import com.squareup.moshi.Json;

/**
 * @author Christian Amani
 */

public class Token
{
    /** The type of the token issued.*/
    @Json(name = "token_type")
    private String tokenType;

    /**The access token issued by the authorization server, and to be used for your API calls,
     * by setting the header as follows: Authorization: Bearer {access_token}. */
    @Json(name = "access_token")
    private String accessToken;
    /** The lifetime in seconds of the access token.*/
    @Json(name = "expires_in")
    private long expiresIn;

    public Token(String tokenType, String accessToken, long expiresIn) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String createAccess() {
        return tokenType +" "+ accessToken;
        // TODO: see a doc concerning Access
    }
}
