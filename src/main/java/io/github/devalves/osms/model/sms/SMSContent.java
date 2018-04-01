package io.github.devalves.osms.model.sms;

/**
 * @author Amani Christian Cyrille Alves
 */
public class SMSContent {
    private String message;

    public SMSContent() {
    }

    public SMSContent(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}