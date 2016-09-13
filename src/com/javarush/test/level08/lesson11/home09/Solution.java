package com.javarush.test.level08.lesson11.home09;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        System.out.println(isDateOdd("JANUARY 2 2020"));
    }

    public static boolean isDateOdd(String date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d yyyy");

        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        try
        {
            cal.setTime(dateFormat.parse(date));
            cal2.set(cal.get(Calendar.YEAR),0,0,0,0,0);

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        int num = (int)((cal.getTimeInMillis()-cal2.getTimeInMillis())/(24*60*60*1000));
        if((num % 2) == 0 )
             return true;
        else
            return false;
    }
}
