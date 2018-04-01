package io.github.devalves.osms.model.error;

import com.squareup.moshi.Json;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class OAuthError {
    private String error;
    @Json(name = "error_description")
    private String errorDescription;

    public OAuthError()
    {}

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
