package io.github.devalves.osms.model;

/**
 * @author Christian Amani
 */
public class ServiceStatistic {
    private String country;
    private CountyStatistic[] countryStatistics;

    public ServiceStatistic()
    {}

    public ServiceStatistic(String country, CountyStatistic[] countryStatistics) {
        this.country = country;
        this.countryStatistics = countryStatistics;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountyStatistic[] getCountryStatistics() {
        return countryStatistics;
    }

    public void setCountryStatistics(CountyStatistic[] countryStatistics) {
        this.countryStatistics = countryStatistics;
    }


    public static class CountyStatistic {
        private  String applicationId; // TODO : verify name later.
        private int usage;

        public CountyStatistic()
        {}

        public CountyStatistic(String applicationId, int usage) {
            this.applicationId = applicationId;
            this.usage = usage;
        }

        public void setApplicationId(String applicationId) {
            this.applicationId = applicationId;
        }

        public void setUsage(int usage) {
            this.usage = usage;
        }

        public String getApplicationId() {
            return this.applicationId;
        }

        public int getUsage() {
            return this.usage;
        }
    }
}
