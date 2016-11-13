package org.akanza.models;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class PartnerContracts
{
    private String partnerId;
    private Contract[] contracts;

    public PartnerContracts()
    {}

    public PartnerContracts(String partnerId, Contract[] contracts)
    {
        this.partnerId = partnerId;
        this.contracts = contracts;
    }

    public String getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId(String partnerId)
    {
        this.partnerId = partnerId;
    }

    public Contract[] getContracts()
    {
        return contracts;
    }

    public void setContracts(Contract[] contracts)
    {
        this.contracts = contracts;
    }


    public static class Contract
    {
        private String service;
        private String contractDescription;
        private ServiceContracts[] serviceContracts;

        public Contract()
        {}

        public Contract(String service, String contractDescription, ServiceContracts[] serviceContracts)
        {
            this.service = service;
            this.contractDescription = contractDescription;
            this.serviceContracts = serviceContracts;
        }


        public String getService()
        {
            return service;
        }

        public void setService(String service)
        {
            this.service = service;
        }

        public String getContractDescription()
        {
            return contractDescription;
        }

        public void setContractDescription(String contractDescription)
        {
            this.contractDescription = contractDescription;
        }

        public ServiceContracts[] getServiceContracts()
        {
            return serviceContracts;
        }

        public void setServiceContracts(ServiceContracts[] serviceContracts)
        {
            this.serviceContracts = serviceContracts;
        }
    }
}

