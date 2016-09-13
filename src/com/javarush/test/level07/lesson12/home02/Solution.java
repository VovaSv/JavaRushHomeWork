package com.javarush.test.level07.lesson12.home02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* Переставить M первых строк в конец списка
Ввести с клавиатуры 2 числа N  и M.
Ввести N строк и заполнить ими список.
Переставить M первых строк в конец списка.
Вывести список на экран, каждое значение с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        int N;
        int M;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<String>();

        N = Integer.parseInt(reader.readLine());
        M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++)
        {
            list.add(reader.readLine());
        }
        List<String> sList1 = list.subList(0,M);
        List<String> sList2 = list.subList(M,list.size());
        List<String> sList3 = new ArrayList<String>();
        sList3.addAll(sList2);
        sList3.addAll(sList1);
        for (int i = 0; i < sList3.size(); i++)
        {
            System.out.println(sList3.get(i));
        }

    }
}
