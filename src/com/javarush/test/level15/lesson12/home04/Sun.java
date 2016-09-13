package com.javarush.test.level15.lesson12.home04;

/**
 * Created by vladimis on 7/6/2016.
 */
public class Sun implements Planet
{

    private static Sun instance;

    private Sun()
    {
    }

    public static Sun getInstance()
    {

        if (null == instance)
        {
            instance = new Sun();
        }
        return instance;
    }
}
