package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        ArrayList<String>[] arrayOfStrings =  new ArrayList[2];
        for (int i = 0; i < arrayOfStrings.length; i++)
        {
                arrayOfStrings[i] = new ArrayList<String>();
        }

        for (int i = 0; i < arrayOfStrings.length; i++)
        {
            for (int j = 0; j < arrayOfStrings.length; j++)
            {
                arrayOfStrings[i].add("as");
            }
        }
        return arrayOfStrings;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}