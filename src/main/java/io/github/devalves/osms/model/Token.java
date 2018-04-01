package io.github.devalves.osms.model;

import com.squareup.moshi.Json;


/**
 * <p>Represents the authentication <b>Token</b> that allows calling <b>ORANGE SMS APIs</b></p>
 *
 * @author Amani Christian Cyrille Alves
 */
public class Token {
    @Json(name = "token_type")
    private String tokenType;


    @Json(name = "access_token")
    private String accessToken;
    @Json(name = "expires_in")
    private long expiresIn;

    /**
     * Generate a Token
     *
     * @param tokenType The type of the token issued
     * @param accessToken The access token issued by the authorization server, and to be used for your API calls,by setting the header as follows: Authorization: Bearer {access_token}.
     * @param expiresIn The lifetime in seconds of the access token
     */
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
