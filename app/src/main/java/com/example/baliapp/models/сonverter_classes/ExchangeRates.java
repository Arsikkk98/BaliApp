package com.example.baliapp.models.сonverter_classes;

public class ExchangeRates {
    private String license;

    private Rates rates;

    private String disclaimer;

    private String timestamp;

    private String base;

    public String getLicense ()
    {
        return license;
    }

    public void setLicense (String license)
    {
        this.license = license;
    }

    public Rates getRates ()
    {
        return rates;
    }

    public void setRates (Rates rates)
    {
        this.rates = rates;
    }

    public String getDisclaimer ()
    {
        return disclaimer;
    }

    public void setDisclaimer (String disclaimer)
    {
        this.disclaimer = disclaimer;
    }

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getBase ()
    {
        return base;
    }

    public void setBase (String base)
    {
        this.base = base;
    }
}
