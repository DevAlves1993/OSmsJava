package org.akanza;

/**
 * Created by Christian Amani on 15/12/2015.
 * <p>A Class which contain the information of SMS</p>
 */
public class SMS
{
    private SMSMessage outBoundSMSMessageRequest;


    /**
     * @param address The Sender address
     * @param senderAddress The current address
     * @param content The content of SMS
     */
    public SMS(String address , String senderAddress,String content)
    {
        outBoundSMSMessageRequest = new SMSMessage(address,senderAddress,content);
    }

    public SMSMessage getOutBoundSMSMessageRequest()
    {
        return outBoundSMSMessageRequest;
    }

    public void setOutBoundSMSMessageRequest(SMSMessage outBoundSMSMessageRequest)
    {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
    }


    /**
     * <p>Class which contains a elements a base of SMS</p>
     */
    public class SMSMessage
    {
        private String address;
        private String senderAddress;
        private SMSContent message;

        public SMSMessage(String address,String senderAddress,String content)
        {
            this.address ="tel:"+address;
            this.senderAddress ="tel:"+ senderAddress;
            this.message = new SMSContent(content);
        }

        public String getMessage()
        {
            return message.getMessage();
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

        public void setMessage(String content)
        {
            message.setMessage(content);
        }

        public String getAddress()
        {
            return address;
        }

        private class SMSContent
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

