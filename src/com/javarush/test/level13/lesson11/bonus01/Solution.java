package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = systemReader.readLine();
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        List<Integer> listNumbers = new ArrayList<>();
        String line = null;
        while ((line = fileReader.readLine()) != null){
            listNumbers.add(Integer.parseInt(line));
        }
        Collections.sort(listNumbers);
        for (int i = 0; i < listNumbers.size(); i++)
        {
            if (listNumbers.get(i)%2 == 0)
                System.out.println(listNumbers.get(i));
        }
    }
}
