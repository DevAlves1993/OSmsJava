package io.github.devalves.osms.model.response;

import io.github.devalves.osms.model.PartnerContracts;

/**
 * @author Christian Amani
 */
public class ContractsSMS
{
    private PartnerContracts partnerContracts;

    public ContractsSMS()
    {}

    public ContractsSMS(PartnerContracts partnerContracts)
    {
        this.partnerContracts = partnerContracts;
    }


    public PartnerContracts getPartnerContracts()
    {
        return partnerContracts;
    }

    public void setPartnerContracts(PartnerContracts partnerContracts)
    {
        this.partnerContracts = partnerContracts;
    }
}

