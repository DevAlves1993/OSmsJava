package org.akanza.models;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class PurchaseOrders
{
    private String purchaseOrderId;
    private String mode;
    private String bundleId;
    private String bundleDescription;
    private String partnerId;
    private Inputs[] inputs;
    private OrderExecutionInformation orderExecutionInformation;

    public PurchaseOrders(String purchaseOrderId,
                          String mode,
                          String bundleId,
                          String bundleDescription,
                          String partnerId,
                          Inputs[] inputs, OrderExecutionInformation orderExecutionInformation)
    {
        this.purchaseOrderId = purchaseOrderId;
        this.mode = mode;
        this.bundleId = bundleId;
        this.bundleDescription = bundleDescription;
        this.partnerId = partnerId;
        this.inputs = inputs;
        this.orderExecutionInformation = orderExecutionInformation;
    }

    public String getPurchaseOrderId()
    {
        return purchaseOrderId;
    }

    public String getMode()
    {
        return mode;
    }

    public String getBundleId()
    {
        return bundleId;
    }

    public String getBundleDescription()
    {
        return bundleDescription;
    }

    public String getPartnerId()
    {
        return partnerId;
    }

    public Inputs[] getInputs()
    {
        return inputs;
    }

    public OrderExecutionInformation getOrderExecutionInformation()
    {
        return orderExecutionInformation;
    }


    public class Inputs
    {
        private String type;
        private String value;

        public Inputs(String type, String value)
        {
            this.type = type;
            this.value = value;
        }

        public String getType()
        {
            return type;
        }

        public String getValue()
        {
            return value;
        }
    }

    public class OrderExecutionInformation
    {
        private String date;
        private String amount;
        private String currency;
        private String service;
        private String country;
        private String contractId;

        public OrderExecutionInformation(String date,
                                         String amount,
                                         String currency,
                                         String service,
                                         String country, String contractId)
        {
            this.date = date;
            this.amount = amount;
            this.currency = currency;
            this.service = service;
            this.country = country;
            this.contractId = contractId;
        }

        public String getDate()
        {
            return date;
        }

        public String getAmount()
        {
            return amount;
        }

        public String getCurrency()
        {
            return currency;
        }

        public String getService()
        {
            return service;
        }

        public String getCountry()
        {
            return country;
        }

        public String getContractId()
        {
            return contractId;
        }
    }
}

