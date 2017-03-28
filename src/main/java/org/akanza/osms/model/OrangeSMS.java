package org.akanza.osms.model;

/**
 * Created by user on 05/02/2017.
 */
public class OrangeSMS
{
    private SMSMessage outboundSMSMessageRequest;


    /**
     * @param address The Sender address
     * @param senderAddress The current address
     * @param content The content of OrangeSMS
     */
    public OrangeSMS(String address , String senderAddress, String content)
    {
        outboundSMSMessageRequest = new SMSMessage(address,senderAddress,content);
    }

    public SMSMessage getOutboundSMSMessageRequest()
    {
        return outboundSMSMessageRequest;
    }

    public void setOutboundSMSMessageRequest(SMSMessage outboundSMSMessageRequest)
    {
        this.outboundSMSMessageRequest = outboundSMSMessageRequest;
    }


    /**
     * <p>Class which contains a elements a base of OrangeSMS</p>
     */
    public static class SMSMessage
    {
        private String address;
        private String senderAddress;
        private String senderName; // TODO verify serialisation
        private SMSContent outboundSMSTextMessage;
        private String resourceURL; // TODO : verify serialisation

        public SMSMessage(String address,String senderAddress,String content)
        {
            this.address ="tel:"+address;
            this.senderAddress ="tel:"+ senderAddress;
            this.outboundSMSTextMessage = new SMSContent(content);
        }

        public String getOutboundSMSTextMessage()
        {
            return outboundSMSTextMessage.getMessage();
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getSenderAddress()
        {
            return senderAddress;
        }

        public void setSenderAddress(String senderAddress)
        {
            this.senderAddress = senderAddress;
        }

        public void setOutboundSMSTextMessage(String content)
        {
            outboundSMSTextMessage.setMessage(content);
        }

        public String getAddress()
        {
            return address;
        }

        public static class SMSContent
        {
            private String message;
            public SMSContent(String message)
            {
                this.message = message;
            }

            public void setMessage(String message)
            {
                this.message = message;
            }

            public String getMessage()
            {
                return this.message;
            }
        }
    }
}
