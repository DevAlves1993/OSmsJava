package org.akanza.responseSms;

import org.akanza.models.PartnerContracts;

/**
 * Created by AMANI on 15/12/2015.
 */
public class RemainderBaseResponse extends BaseResponse
{
    private PartnerContracts partnerContracts;

    public RemainderBaseResponse(PartnerContracts partnerContracts)
    {
        this.partnerContracts = partnerContracts;
    }

    public PartnerContracts getPartnerContracts()
    {
        return partnerContracts;
    }
}

