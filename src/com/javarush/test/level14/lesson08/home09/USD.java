package com.javarush.test.level14.lesson08.home09;

/**
 * Created by VLADIMIS on 5/15/2016.
 */
public class USD extends Money
{
    public USD(double amount)
    {
        super(amount);
    }

    @Override
    public String getCurrencyName()
    {
        return "USD";
    }
}
