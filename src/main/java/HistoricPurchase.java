import Other.PurchaseOrders;

/**
 * Created by AMANI on 15/12/2015.
 */
public class HistoricPurchase
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
