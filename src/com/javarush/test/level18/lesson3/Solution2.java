package com.javarush.test.level18.lesson3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution2 {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);
        int min = 0;
        if(inputStream.available() > 0)
            min = inputStream.read();
        System.out.println(min);
        while (inputStream.available() > 0){
            int in = inputStream.read();
            System.out.println(in);
            if(in < min)
                min = in;
        }
        System.out.println(min);
        reader.close();
        inputStream.close();
    }
}
