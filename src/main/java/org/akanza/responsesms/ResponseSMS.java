package org.akanza.responsesms;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class ResponseSMS extends BaseResponse
{
    private SMSResponse outBoundSMSMessageRequest;

    public ResponseSMS()
    {}

    public ResponseSMS(SMSResponse outBoundSMSMessageRequest)
    {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
    }

    public SMSResponse getOutBoundSMSMessageRequest()
    {
        return outBoundSMSMessageRequest;
    }

    public void setOutBoundSMSMessageRequest(SMSResponse outBoundSMSMessageRequest)
    {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
    }


    public static class SMSResponse
    {
        private String address;
        private SMSContent outboundSMSTextMessage;
        private String senderAddress;
        private String senderName;

        public SMSResponse()
        {}

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public SMSContent getOutboundSMSTextMessage()
        {
            return outboundSMSTextMessage;
        }

        public void setOutboundSMSTextMessage(SMSContent outboundSMSTextMessage)
        {
            this.outboundSMSTextMessage = outboundSMSTextMessage;
        }

        public String getSenderAddress()
        {
            return senderAddress;
        }

        public void setSenderAddress(String senderAddress)
        {
            this.senderAddress = senderAddress;
        }

        public String getSenderName()
        {
            return senderName;
        }

        public void setSenderName(String senderName)
        {
            this.senderName = senderName;
        }

        private static class SMSContent
        {
            private String message;

            public SMSContent()
            {}

            public void setMessage(String message)
            {
                this.message = message;
            }

            public String getMessage()
            {
                return message;
            }
        }
    }
}

