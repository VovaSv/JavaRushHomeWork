package com.javarush.test.level15.lesson12.home04;

/**
 * Created by vladimis on 7/6/2016.
 */
public class Earth implements Planet
{
    private static Earth instance;
    private Earth(){}

    public static Earth getInstance(){

        if(null == instance){
            instance = new Earth();
        }
        return instance;
    }

}
