package org.akanza.models;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class ServiceContracts
{
    private String country;
    private String service;
    private String contractId;
    private int availableUnits;
    private String expires;
    private String scDescription;


    public ServiceContracts()
    {}

    public ServiceContracts(String country,
                            String service,
                            String contractId,
                            int availableUnits,
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

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getService()
    {
        return service;
    }

    public void setService(String service)
    {
        this.service = service;
    }

    public String getContractId()
    {
        return contractId;
    }

    public void setContractId(String contractId)
    {
        this.contractId = contractId;
    }

    public int getAvailableUnits()
    {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits)
    {
        this.availableUnits = availableUnits;
    }

    public String getExpires()
    {
        return expires;
    }

    public void setExpires(String expires)
    {
        this.expires = expires;
    }

    public String getScDescription()
    {
        return scDescription;
    }

    public void setScDescription(String scDescription)
    {
        this.scDescription = scDescription;
    }
}

