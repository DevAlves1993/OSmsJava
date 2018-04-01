package io.github.devalves.osms.model.error;

/**
 *
 * <p>Represent an error which can arise during calls it of the SMS API.</p>
 * @author Amani Christian Cyrille Alves
 */
public class ResponseError
{
    private Error error;

    public ResponseError()
    {}

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getCode() {
        return error.getCode();
    }

    public String getMessage() {
        return error.getMessage();
    }

    public String getDescription() {
        return error.getDescription();
    }

    public static class Error {
        /** Code error */
        private String code;
        /** Message error*/
        private String message;
        /** Description error */
        private String description;

        public Error()
        {}

        public Error(String code,String message,String description) {
            this.code = code;
            this.message = message;
            this.description = description;
        }

        public String getCode()
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

        public void setCode(String code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
