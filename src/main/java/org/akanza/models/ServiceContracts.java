package org.akanza.models;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class ServiceContracts
{
    private String country;
    private String service;
    private String contractId;
    private String availableUnits;
    private String expires;
    private String scDescription;

    public ServiceContracts(String country,
                            String service,
                            String contractId,
                            String availableUnits,
                            String expires, String scDescription)
    {
        this.country = country;
        this.service = service;
        this.contractId = contractId;
        this.availableUnits = availableUnits;
        this.expires = expires;
        this.scDescription = scDescription;
    }

    public String getCountry()
    {
        return country;
    }

    public String getService()
    {
        return service;
    }

    public String getContractId()
    {
        return contractId;
    }

    public String getAvailableUnits()
    {
        return availableUnits;
    }

    public String getExpires()
    {
        return expires;
    }

    public String getScDescription()
    {
        return scDescription;
    }
}

