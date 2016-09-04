package org.akanza.responseSms;

/**
 * Created by Christian Amani on 25/08/2016.
 */
public class ResponseSubscription extends BaseResponse
{
    ReceiptSubscription deliveryReceiptSubscription;
    public ResponseSubscription()
    {}
    public class ReceiptSubscription
    {
        CallbackRef callbackReference;
        String resourceURL;
        public ReceiptSubscription()
        {}
        public class CallbackRef
        {
            String notifyURL;
            public CallbackRef()
            {}
        }
    }
}
