package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        int idx = url.indexOf("?");
        String query = url.substring(idx + 1);
        List<String> pairs = new ArrayList<>();
        pairs = Arrays.asList(query.split("&"));
        List<String> objs = new ArrayList<>();
        for (String pair : pairs)
        {
            String[] values = pair.split("=");
                if (values.length > 1)
                {
                    if("obj".equals(values[0])){

                            objs.add(values[1]);
                    }
                    System.out.print(values[0]);
                    System.out.print(" ");

                } else
                {
                    System.out.print(values[0]);
                    System.out.print(" ");
                }
        }

        System.out.println('\n');

        for(String str : objs){
            if(isNumeric(str))
                alert(Double.valueOf(str));
            else{
                alert(str);
            }
        }
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
