package com.javarush.test.level15.lesson12.home04;

/**
 * Created by vladimis on 7/6/2016.
 */
public class Moon implements Planet
{

    private static Moon instance;

    private Moon(){}

    public static Moon getInstance(){

        if(null == instance){
            instance = new Moon();
        }
        return instance;
    }
}
