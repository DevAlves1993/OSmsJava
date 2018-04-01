package io.github.devalves.osms.model.partnercontract;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class PartnerContracts {
    private String partnerId;
    private Contract[] contracts;

    public PartnerContracts()
    {}

    public PartnerContracts(String partnerId, Contract[] contracts) {
        this.partnerId = partnerId;
        this.contracts = contracts;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Contract[] getContracts() {
        return contracts;
    }

    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
    }

}

