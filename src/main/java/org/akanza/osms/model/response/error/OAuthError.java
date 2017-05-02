package org.akanza.osms.model.response.error;

import com.squareup.moshi.Json;

/**
 * Created by Christian Amani on 01/04/2017.
 */
public class OAuthError
{
    private String error;
    @Json(name = "error_description")
    private String errorDescription;

    public OAuthError()
    {}

    public String getError()
    {
        return error;
    }

    public String getErrorDescription()
    {
        return errorDescription;
    }
}
