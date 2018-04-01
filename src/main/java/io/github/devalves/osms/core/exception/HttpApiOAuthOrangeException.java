package io.github.devalves.osms.core.exception;

import io.github.devalves.osms.model.error.OAuthError;

/**
 *
 *
 * @author Amani Christian Cyrille Alves
 */
public class HttpApiOAuthOrangeException extends RuntimeException {
    private OAuthError error;

    public HttpApiOAuthOrangeException(OAuthError error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return error.getError();
    }

    public String getDescription() {
        return error.getErrorDescription();
    }

}
