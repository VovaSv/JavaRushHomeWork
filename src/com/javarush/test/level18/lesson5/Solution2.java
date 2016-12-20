package com.javarush.test.level18.lesson5;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2 {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream inputFile = new FileInputStream(fileName);
        int count = 0;
        while (inputFile.available() > 0 ){
            if(",".equals(Character.toString((char)inputFile.read())))
                count++;
        }
        reader.close();
        System.out.println(count);
    }
}
