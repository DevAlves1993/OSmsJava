package org.akanza.models;

/**
 * Created by AMANI on 15/12/2015.
 */
public class PartnerContracts
{
    private String partnerId;
    private Contract[] contracts;

    public PartnerContracts(String partnerId, Contract[] contracts)
    {
        this.partnerId = partnerId;
        this.contracts = contracts;
    }

    public Contract[] getContracts()
    {
        return contracts;
    }

    public String getPartnerId()
    {
        return partnerId;
    }

    public class Contract
    {
        private String service;
        private String contractDescription;
        private ServiceContracts[] serviceContracts;

        public Contract(String service, String contractDescription, ServiceContracts[] serviceContracts)
        {
            this.service = service;
            this.contractDescription = contractDescription;
            this.serviceContracts = serviceContracts;
        }

        public ServiceContracts[] getServiceContracts()
        {
            return serviceContracts;
        }

        public String getContractDescription()
        {
            return contractDescription;
        }

        public String getService()
        {
            return service;
        }
    }
}

