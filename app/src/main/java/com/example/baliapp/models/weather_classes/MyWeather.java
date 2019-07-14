package com.example.baliapp.models.weather_classes;

public class MyWeather {
    private String count;

    private String cod;

    private String message;

    private ListWeather[] list;

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ListWeather[] getList ()
    {
        return list;
    }

    public void setList (ListWeather[] list)
    {
        this.list = list;
    }

}
