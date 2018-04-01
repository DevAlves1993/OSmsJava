package io.github.devalves.osms.model.subscription;

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
}
