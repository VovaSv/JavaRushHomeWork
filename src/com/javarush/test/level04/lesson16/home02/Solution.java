package com.javarush.test.level04.lesson16.home02;

import java.io.*;
import java.nio.Buffer;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num1 = Integer.parseInt(reader.readLine());
        int num2 = Integer.parseInt(reader.readLine());
        int num3 = Integer.parseInt(reader.readLine());

        System.out.println(midNumber(num1,num2,num3));

}

    public static int midNumber(int num1, int num2, int num3)
    {

        if (num1 > num2 && num1 > num3 && num2 > num3)
            return num2;
        if (num1 > num2 && num1 > num3 && num3 > num2)
            return num3;
        if (num2 > num3 && num2 > num1 && num3 > num1)
            return num3;
        if (num2 > num3 && num2 > num1 && num1 > num3)
            return num3;
        if (num3 > num2 && num3 > num1 && num1 > num2)
            return num1;
        else
        {
            return num2;
        }
    }
}