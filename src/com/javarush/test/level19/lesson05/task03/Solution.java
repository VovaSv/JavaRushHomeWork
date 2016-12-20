package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        BufferedReader readerNew = new BufferedReader(new FileReader(fileName1));
        FileWriter fileWriter = new FileWriter(fileName2);
        while (readerNew.ready())
        {
            String[] arr = readerNew.readLine().split("[\\p{Blank} ]");
            for (int i = 0; i < arr.length; i++)
            {
                try
                {
                    int num = Integer.valueOf(arr[i]);
                    fileWriter.write(String.valueOf(num));
                    if(i < arr.length-1)
                    fileWriter.write(" ");
                }
                catch (NumberFormatException e){

                }
            }
        }
        readerNew.close();
        fileWriter.close();

    }
}
