package io.github.devalves.osms.model.response;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class ResponseSubscription {
    private ReceiptSubscription deliveryReceiptSubscription;
    public ResponseSubscription()
    {}

    public ResponseSubscription(String partnerHost) {
        ReceiptSubscription.CallbackRef callbackRef = new ReceiptSubscription
                .CallbackRef(partnerHost);
    }

    public ReceiptSubscription getDeliveryReceiptSubscription() {
        return deliveryReceiptSubscription;
    }

    public void setDeliveryReceiptSubscription(ReceiptSubscription deliveryReceiptSubscription) {
        this.deliveryReceiptSubscription = deliveryReceiptSubscription;
    }

    public static class ReceiptSubscription {
        private CallbackRef callbackReference;
        private String resourceURL;
        public ReceiptSubscription()
        {}

        public void setCallbackReference(CallbackRef callbackReference) {
            this.callbackReference = callbackReference;
        }

        public void setResourceURL(String resourceURL) {
            this.resourceURL = resourceURL;
        }

        public CallbackRef getCallbackReference() {
            return callbackReference;
        }

        public String getResourceURL() {
            return resourceURL;
        }

        public static class CallbackRef {
            private String notifyURL;
            public CallbackRef()
            {}

            public CallbackRef(String partnerHost) {
                notifyURL = "https://"+partnerHost+"/orange/smsdr.php";
            }

            public void setNotifyURL(String notifyURL) {
                this.notifyURL = notifyURL;
            }

            public String getNotifyURL() {
                return notifyURL;
            }
        }
    }
}
