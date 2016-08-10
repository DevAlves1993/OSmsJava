package org.akanza.responseSms;

import org.akanza.models.PartnerContracts;

/**
 * Created by AMANI on 15/12/2015.
 */
public class RemainderSMS extends BaseResponse
{
    private PartnerContracts partnerContracts;

    public RemainderSMS(PartnerContracts partnerContracts)
    {
        this.partnerContracts = partnerContracts;
    }

    public PartnerContracts getPartnerContracts()
    {
        return partnerContracts;
    }
}

