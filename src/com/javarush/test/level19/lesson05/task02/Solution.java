package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader readerFile = new BufferedReader(new FileReader(fileName));
        int count  = 0;
        while (readerFile.ready()){
            String line = readerFile.readLine();
            String[] arr = line.split("[\\p{Punct} || \\p{Blank} ]");
            for (int i = 0; i < arr.length; i++)
            {
                if(arr[i].toLowerCase().equals("world"))
                    count++;
            }
        }
        readerFile.close();
        System.out.println(count);
    }
}
