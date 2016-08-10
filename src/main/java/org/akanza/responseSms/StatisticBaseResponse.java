package org.akanza.responseSms;

import org.akanza.models.PartnerStatistics;

/**
 * Created by AMANI on 15/12/2015.
 */
public class StatisticBaseResponse extends BaseResponse
{
    private PartnerStatistics partnerStatistics;

    public StatisticBaseResponse(PartnerStatistics partnerStatistics)
    {
        this.partnerStatistics = partnerStatistics;
    }

    public PartnerStatistics getPartnerStatistics()
    {
        return partnerStatistics;
    }
}
