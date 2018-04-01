package io.github.devalves.osms.model.sms;

/**
 * @author Amani Christian Cyrille Alves
 */
public  class SMSResponse {
    private String address;
    private SMSContent outboundSMSTextMessage;
    private String senderAddress;
    private String senderName;

    public SMSResponse()
    {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SMSContent getOutboundSMSTextMessage() {
        return outboundSMSTextMessage;
    }

    public void setOutboundSMSTextMessage(SMSContent outboundSMSTextMessage) {
        this.outboundSMSTextMessage = outboundSMSTextMessage;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

}