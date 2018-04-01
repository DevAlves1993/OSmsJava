package io.github.devalves.osms.model.response;

import io.github.devalves.osms.model.PartnerStatistics;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class StatisticSMS
{
    private PartnerStatistics partnerStatistics;

    public StatisticSMS()
    {}

    public StatisticSMS(PartnerStatistics partnerStatistics) {
        this.partnerStatistics = partnerStatistics;
    }

    public PartnerStatistics getPartnerStatistics() {
        return partnerStatistics;
    }

    public void setPartnerStatistics(PartnerStatistics partnerStatistics) {
        this.partnerStatistics = partnerStatistics;
    }
}
