package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        String fileName1 = args[0];
        String fileName2 = args[1];

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        FileWriter writer = new FileWriter(fileName2);
        StringBuilder builder = new StringBuilder();
        while (reader.ready()){
            String[] arr = reader.readLine().split(" ");
            for (int i = 0; i < arr.length; i++)
            {
                if(null != arr[i] && arr[i].length() > 6)
                {
                    builder.append(arr[i] + ",");
                }
            }

        }
        writer.write(builder.toString().substring(0,builder.length()-1));
        reader.close();
        writer.close();

    }
}
