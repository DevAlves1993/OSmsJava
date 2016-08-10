package org.akanza.responseSms;

import org.akanza.models.PartnerStatistics;

/**
 * Created by AMANI on 15/12/2015.
 */
public class StatisticSMS extends BaseResponse
{
    private PartnerStatistics partnerStatistics;

    public StatisticSMS(PartnerStatistics partnerStatistics)
    {
        this.partnerStatistics = partnerStatistics;
    }

    public PartnerStatistics getPartnerStatistics()
    {
        return partnerStatistics;
    }
}
