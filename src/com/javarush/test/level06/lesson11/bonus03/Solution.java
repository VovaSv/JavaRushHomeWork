package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = { Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine())};
        for (int i = 0; i < arr.length-1; i++)
        {
            int index = i;
            int min;
            for (int j = i; j < arr.length; j++)
            {
                if(arr[j] < arr[i])
                {
                    index = j;
                }
            }
                min = arr[index];
                arr[index] = arr[i];
                arr[i] = min;

        }
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println(arr[i]);
        }

    }


}
