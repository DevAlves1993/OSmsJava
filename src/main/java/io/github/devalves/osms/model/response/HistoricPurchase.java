package io.github.devalves.osms.model.response;

import io.github.devalves.osms.model.PurchaseOrder;

/**
 * @author Christian Amani
 */
public class HistoricPurchase
{
    private PurchaseOrder[] purchaseOrders;

    public HistoricPurchase()
    {}

    public HistoricPurchase(PurchaseOrder[] purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public PurchaseOrder[] getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(PurchaseOrder[] purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
