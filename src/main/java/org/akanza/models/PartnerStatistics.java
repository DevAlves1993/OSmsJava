package org.akanza.models;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class PartnerStatistics
{
    private String partnerId;
    private Statistic[] statistics;

    public PartnerStatistics(String partnerId, Statistic[] statistics)
    {
        this.partnerId = partnerId;
        this.statistics = statistics;
    }

    public String getPartnerId()
    {
        return partnerId;
    }
    public  Statistic[] getStatistics()
    {
        return statistics;
    }

    public class Statistic
    {
        private String service;
        private ServiceStatistic[] serviceStatistics;

        public Statistic(String service, ServiceStatistic[] serviceStatistics)
        {
            this.service = service;
            this.serviceStatistics = serviceStatistics;
        }

        public String getService()
        {
            return service;
        }
        public ServiceStatistic[]  getServiceStatistics()
        {
            return serviceStatistics;
        }
    }
}

