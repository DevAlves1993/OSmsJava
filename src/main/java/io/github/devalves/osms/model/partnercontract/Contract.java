package io.github.devalves.osms.model.partnercontract;

/**
 * @author Amani Christian Cyrille Alves
 */
public class Contract {
    private String service;
    private String contractDescription;
    private ServiceContracts[] serviceContracts;

    public Contract()
    {}

    public Contract(String service, String contractDescription
            , ServiceContracts[] serviceContracts) {
        this.service = service;
        this.contractDescription = contractDescription;
        this.serviceContracts = serviceContracts;
    }


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }

    public ServiceContracts[] getServiceContracts() {
        return serviceContracts;
    }

    public void setServiceContracts(ServiceContracts[] serviceContracts) {
        this.serviceContracts = serviceContracts;
    }
}