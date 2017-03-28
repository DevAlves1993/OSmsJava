package org.akanza.osms.model.response;

import org.akanza.osms.model.PartnerStatistics;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class StatisticSMS
{
    private PartnerStatistics partnerStatistics;

    public StatisticSMS()
    {}

    public StatisticSMS(PartnerStatistics partnerStatistics)
    {
        this.partnerStatistics = partnerStatistics;
    }

    public PartnerStatistics getPartnerStatistics()
    {
        return partnerStatistics;
    }

    public void setPartnerStatistics(PartnerStatistics partnerStatistics)
    {
        this.partnerStatistics = partnerStatistics;
    }
}
