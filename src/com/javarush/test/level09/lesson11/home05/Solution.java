package com.javarush.test.level09.lesson11.home05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Гласные и согласные буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа должна вывести на экран две строки:
1. первая строка содержит только гласные буквы
2. вторая - только согласные буквы и знаки препинания из введённой строки.
Буквы соединять пробелом, каждая строка должна заканчиваться пробелом.

Пример ввода:
Мама мыла раму.
Пример вывода:
а а ы а а у
М м м л р м .
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        List<Character> vowelChar = new ArrayList<>();
        List<Character> consChar = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        for (int i = 0; i < str.length(); i++)
        {
            if(isVowel(str.charAt(i))){
                vowelChar.add(str.charAt(i));
            }
            else if(!Character.isWhitespace(str.charAt(i))){

                consChar.add(str.charAt(i));
            }
        }
        for (int i = 0; i < vowelChar.size(); i++)
        {
            System.out.print(vowelChar.get(i));
                if(i < (vowelChar.size()-1))
                System.out.print(" ");
        }
        System.out.print('\n');
        for (int i = 0; i < consChar.size(); i++)
        {
            System.out.print(consChar.get(i));
            if(i < (consChar.size()-1))
                System.out.print(" ");
        }

    }


    public static char[] vowels = new char[]{'а', 'я', 'у', 'ю', 'и', 'ы', 'э', 'е', 'о', 'ё'};

    //метод проверяет, гласная ли буква
    public static boolean isVowel(char c)
    {
        c = Character.toLowerCase(c);  //приводим символ в нижний регистр - от заглавных к строчным буквам

        for (char d : vowels)   //ищем среди массива гласных
        {
            if (c == d)
                return true;
        }
        return false;
    }
}
