package org.akanza.osms.core;

/**
 * Created by Christian Amani on 29/03/2017.
 */
public enum CountryCode
{
    botswana("+267","Botswana"),
    cameroon("+237","Cameroon"),
    ivoryCoast("+225","Ivory Coast"),
    RDCongo("+243","RD Congo"),
    egypt("+20","Egypt"),
    guineaConakry("+224","Guinea Conakry"),
    mali("+223","Mali"),
    niger("+227","Niger"),
    senegal("+221","Senegal");

    private String code;
    private String country;
    CountryCode(String code,String country)
    {
        this.code = code;
        this.country = country;
    }


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "Country : "+country+";Code : "+code;
    }
}
