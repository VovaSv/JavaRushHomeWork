package com.javarush.test.level18.lesson10.home06;

/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException{
        FileInputStream inputStream = new FileInputStream(args[0]);
        int [] asciiArr = new int [127];
        while (inputStream.available() > 0){
            asciiArr[inputStream.read()] ++;
        }

        for (int i = 0; i < asciiArr.length; i++)
        {
            if(asciiArr[i] > 0)
                System.out.println((char)i + " " + asciiArr[i]);
        }
        inputStream.close();
    }
}
