package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        String fileName = args[0];

        try
        {
            FileInputStream inputStream = new FileInputStream(fileName);
            int count = 0;
            while (inputStream.available() > 0){
                int in = inputStream.read();
                if((65 <= in && in <= 90) || (in >= 97 && in <= 122))
                    count++;
            }
            System.out.println(count);
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }


}
