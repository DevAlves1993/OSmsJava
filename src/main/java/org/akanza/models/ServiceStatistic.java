package org.akanza.models;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class ServiceStatistic
{
    private String country;
    private  CountyStatistic[] countryStatistics;

    public ServiceStatistic(String country, CountyStatistic[] countryStatistics)
    {
        this.country = country;
        this.countryStatistics = countryStatistics;
    }

    public String getCountry()
    {
        return country;
    }
    public CountyStatistic[] getCountyStatistics()
    {
        return countryStatistics;
    }

    public class CountyStatistic
    {
        private  String applicationId; // TODO : verify name later.
        private int usage;

        public CountyStatistic(String applicationId, int usage)
        {
            this.applicationId = applicationId;
            this.usage = usage;
        }

        public String getApplicationId()
        {
            return this.applicationId;
        }
        public int getUsage()
        {
            return this.usage;
        }
    }
}
