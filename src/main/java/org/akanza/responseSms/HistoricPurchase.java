package org.akanza.responseSms;

import org.akanza.models.PurchaseOrders;

/**
 * Created by AMANI on 15/12/2015.
 */
public class HistoricPurchase extends BaseResponse
{
    private PurchaseOrders[] purchaseOrders;

    public HistoricPurchase(PurchaseOrders[] purchaseOrders)
    {
        this.purchaseOrders = purchaseOrders;
    }

    public PurchaseOrders[] getPurchaseOrders()
    {
        return purchaseOrders;
    }
}
