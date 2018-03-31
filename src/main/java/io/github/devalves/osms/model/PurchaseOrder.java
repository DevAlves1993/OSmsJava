package io.github.devalves.osms.model;

/**
 * @author Christian Amani
 */
public class PurchaseOrder
{
    private String purchaseOrderId;
    private String mode;
    private String bundleId;
    private String bundleDescription;
    private String partnerId;
    private Input[] inputs;
    private OrderExecutionInformation orderExecutionInformation;

    public PurchaseOrder()
    {}

    public PurchaseOrder(String purchaseOrderId, String mode, String bundleId
            , String bundleDescription, String partnerId, Input[] inputs
            , OrderExecutionInformation orderExecutionInformation) {
        this.purchaseOrderId = purchaseOrderId;
        this.mode = mode;
        this.bundleId = bundleId;
        this.bundleDescription = bundleDescription;
        this.partnerId = partnerId;
        this.inputs = inputs;
        this.orderExecutionInformation = orderExecutionInformation;
    }

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getBundleDescription() {
        return bundleDescription;
    }

    public void setBundleDescription(String bundleDescription) {
        this.bundleDescription = bundleDescription;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Input[] getInputs() {
        return inputs;
    }

    public void setInputs(Input[] inputs) {
        this.inputs = inputs;
    }

    public OrderExecutionInformation getOrderExecutionInformation() {
        return orderExecutionInformation;
    }

    public void setOrderExecutionInformation(OrderExecutionInformation orderExecutionInformation)
    {
        this.orderExecutionInformation = orderExecutionInformation;
    }


    public static class Input {
        private String type;
        private String value;

        public Input(String type, String value) {
            this.type = type;
            this.value = value;
        }


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }
    }

    public static class OrderExecutionInformation {
        private String date;
        private int amount;
        private String currency;
        private String service;
        private String country;
        private String contractId;

        public OrderExecutionInformation()
        {}

        public OrderExecutionInformation(String date, int amount, String currency, String service,
                                         String country, String contractId) {
            this.date = date;
            this.amount = amount;
            this.currency = currency;
            this.service = service;
            this.country = country;
            this.contractId = contractId;
        }


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
            this.contractId = contractId;
        }
    }
}

