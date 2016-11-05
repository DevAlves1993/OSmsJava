package org.akanza.responseSms;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class ResponseSMS extends BaseResponse
{
    private SMSResponse outBoundSMSMessageRequest;

    public ResponseSMS(SMSResponse outBoundSMSMessageRequest)
    {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
    }

    public SMSResponse getOutBoundSMSMessageRequest()
    {
        return outBoundSMSMessageRequest;
    }

    public class SMSResponse
    {
        private String address;
        private SMSContent outboundSMSTextMessage;
        private String senderAddress;
        private String senderName;

        public String getAddress()
        {
            return address;
        }

        public String getOutboundSMSTextMessage()
        {
            return outboundSMSTextMessage.getMessage();
        }

        public String getSenderAddress()
        {
            return senderAddress;
        }

        public String getSenderName()
        {
            return senderName;
        }

        private class SMSContent
        {
            private String message;

            public String getMessage()
            {
                return message;
            }
        }
    }
}

