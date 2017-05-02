package org.akanza.osms.model.response;

import org.akanza.osms.model.PurchaseOrder;

/**
 * @author Christian Amani
 */
public class HistoricPurchase
{
    private PurchaseOrder[] purchaseOrders;

    public HistoricPurchase()
    {}

    public HistoricPurchase(PurchaseOrder[] purchaseOrders)
    {
        this.purchaseOrders = purchaseOrders;
    }

    public PurchaseOrder[] getPurchaseOrders()
    {
        return purchaseOrders;
    }

    public void setPurchaseOrders(PurchaseOrder[] purchaseOrders)
    {
        this.purchaseOrders = purchaseOrders;
    }
}
