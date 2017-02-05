package org.akanza.responsesms;

/**
 * Created by Christian Amani on 25/08/2016.
 */
public class ResponseSubscription extends BaseResponse
{
    private ReceiptSubscription deliveryReceiptSubscription;
    public ResponseSubscription()
    {}

    public ReceiptSubscription getDeliveryReceiptSubscription()
    {
        return deliveryReceiptSubscription;
    }

    public void setDeliveryReceiptSubscription(ReceiptSubscription deliveryReceiptSubscription)
    {
        this.deliveryReceiptSubscription = deliveryReceiptSubscription;
    }

    public static class ReceiptSubscription
    {
        private CallbackRef callbackReference;
        private String resourceURL;
        public ReceiptSubscription()
        {}

        public void setCallbackReference(CallbackRef callbackReference)
        {
            this.callbackReference = callbackReference;
        }

        public void setResourceURL(String resourceURL)
        {
            this.resourceURL = resourceURL;
        }

        public CallbackRef getCallbackReference()
        {
            return callbackReference;
        }

        public String getResourceURL()
        {
            return resourceURL;
        }

        public static class CallbackRef
        {
            private String notifyURL;
            public CallbackRef()
            {}

            public void setNotifyURL(String notifyURL)
            {
                this.notifyURL = notifyURL;
            }

            public String getNotifyURL()
            {
                return notifyURL;
            }
        }
    }
}
