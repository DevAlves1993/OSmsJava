package io.github.devalves.osms.model.sms;

/**
 * @author Amani Christian Cyrille Alves
 */
public class ResponseSMS {

    private SMSResponse outBoundSMSMessageRequest;

    public ResponseSMS() {
    }

    public ResponseSMS(SMSResponse outBoundSMSMessageRequest) {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
    }

    public SMSResponse getOutBoundSMSMessageRequest() {
        return outBoundSMSMessageRequest;
    }

    public void setOutBoundSMSMessageRequest(SMSResponse outBoundSMSMessageRequest) {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
    }


}

