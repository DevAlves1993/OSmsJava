package Other;

/**
 * Created by AMANI on 15/12/2015.
 */
public class ServiceStatistics
{
    private String country;
    private  CountyStatistics[] countryStatistics;

    public ServiceStatistics(String country, CountyStatistics[] countryStatistics)
    {
        this.country = country;
        this.countryStatistics = countryStatistics;
    }

    public String getCountry()
    {
        return country;
    }
    public CountyStatistics[] getCountyStatistics()
    {
        return countryStatistics;
    }

    public class CountyStatistics
    {
        private  String applicationId;
        private  String usage;

        public CountyStatistics(String applicationId, String usage)
        {
            this.applicationId = applicationId;
            this.usage = usage;
        }

        public String getApplicationId()
        {
            return this.applicationId;
        }
        public String getUsage()
        {
            return this.usage;
        }
    }
}
