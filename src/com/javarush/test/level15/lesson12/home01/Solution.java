package com.javarush.test.level15.lesson12.home01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Разные методы для разных типов
1. Считать с консоли данные, пока не введено слово "exit".
2. Для каждого значения, кроме "exit", вызвать метод print. Если значение:
2.1. содержит точку '.', то вызвать метод print для Double;
2.2. больше нуля, но меньше 128, то вызвать метод print для short;
2.3. больше либо равно 128, то вызвать метод print для Integer;
2.4. иначе, вызвать метод print для String.
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        List<String> list = new ArrayList<>();
        while(!"exit".equals(str = reader.readLine()))
        {
            list.add(str);
        }
        for (int i = 0; i < list.size(); i++)
        {
            if(checkForDot(list.get(i))){
                print(Double.parseDouble(list.get(i)));

            }
            else if(isNumber(list.get(i)) && isShort(list.get(i))){
                print(Short.parseShort(list.get(i)));

            }
            else if(isNumber(list.get(i)) && isInteger(list.get(i))){
                print(Integer.parseInt(list.get(i)));

            }
            else{
                print(list.get(i));

            }
        }
    }

    private static boolean isInteger(String str)
    {
        try
        {
            int i = Integer.parseInt(str);
            if( i >= 128)
                return true;
            return false;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean isShort(String str)
    {
        try
        {

            short s = Short.parseShort(str);
            if( 0 < s && s < 128)
                return true;
            return false;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean isDouble(String str)
    {
        try
        {
            if(str.matches("[0-9]+(\\.[0-9]+)?"))
            {
                double d = Double.valueOf(str);
                return true;
            }
            return false;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean isNumber(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean checkForDot(String str)
    {
        return str.contains(".");
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
