package io.github.devalves.osms.model.subscription;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class ReceiptSubscription {

    private ReceiptSubscription.CallbackRef callbackReference;
    private String resourceURL;

    public ReceiptSubscription() {
    }

    public void setCallbackReference(ReceiptSubscription.CallbackRef callbackReference) {
        this.callbackReference = callbackReference;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public ReceiptSubscription.CallbackRef getCallbackReference() {
        return callbackReference;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public static class CallbackRef {
        private String notifyURL;

        public CallbackRef() {
        }

        public CallbackRef(String partnerHost) {
            notifyURL = "https://" + partnerHost + "/orange/smsdr.php";
        }

        public void setNotifyURL(String notifyURL) {
            this.notifyURL = notifyURL;
        }

        public String getNotifyURL() {
            return notifyURL;
        }
    }
}