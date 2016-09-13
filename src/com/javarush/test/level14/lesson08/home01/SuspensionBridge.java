package com.javarush.test.level14.lesson08.home01;

/**
 * Created by VLADIMIS on 4/26/2016.
 */
public class SuspensionBridge implements Bridge
{
    private final int count = 2;
    @Override
    public int getCarsCount()
    {
        return this.count;
    }

}
