package com.javarush.test.level14.lesson08.bonus01;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* Нашествие эксепшенов
Заполни массив exceptions 10 различными эксепшенами.
Первое исключение уже реализовано в методе initExceptions.
*/

public class Solution
{
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args)
    {
        initExceptions();

        for (Exception exception : exceptions)
        {
            System.out.println(exception);
        }
    }


    private static void initExceptions()
    {   //it's first exception
        try
        {
            float i = 1 / 0;

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            Object x = new Integer(0);
            System.out.println((String)x);

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            int num[]={1,2,3,4};
            System.out.println(num[5]);

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
           InputStream file = new FileInputStream(new File("c:Asd"));
            byte x = (byte) file.read();

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            Object obj = null;
            obj.hashCode();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

        try
        {
            throw new IllegalArgumentException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            throw new StringIndexOutOfBoundsException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            throw new NumberFormatException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            throw new IllegalAccessException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }
        try
        {
            throw new ArrayStoreException();

        } catch (Exception e)
        {
            exceptions.add(e);
        }

    }
}
