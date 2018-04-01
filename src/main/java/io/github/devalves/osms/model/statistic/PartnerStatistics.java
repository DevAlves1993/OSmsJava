package io.github.devalves.osms.model.statistic;

/**
 *
 * @author Amani Christian Cyrille Alves
 */
public class PartnerStatistics {
    private String partnerId;
    private Statistic[] statistics;

    public PartnerStatistics()
    {}

    public PartnerStatistics(String partnerId, Statistic[] statistics) {
        this.partnerId = partnerId;
        this.statistics = statistics;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Statistic[] getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistic[] statistics) {
        this.statistics = statistics;
    }


    public static class Statistic {
        private String service;
        private ServiceStatistic[] serviceStatistics;

        public Statistic()
        {}

        public Statistic(String service, ServiceStatistic[] serviceStatistics) {
            this.service = service;
            this.serviceStatistics = serviceStatistics;
        }


        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public ServiceStatistic[] getServiceStatistics() {
            return serviceStatistics;
        }

        public void setServiceStatistics(ServiceStatistic[] serviceStatistics) {
            this.serviceStatistics = serviceStatistics;
        }
    }
}

