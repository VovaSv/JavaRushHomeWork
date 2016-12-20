package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(fileName1);
        FileWriter fileWriter = new FileWriter(fileName2);
        BufferedReader reader1 = new BufferedReader(fileReader);
        while (reader1.ready()){
            String line = reader1.readLine().replaceAll("\\.","\\!");
            fileWriter.write(line);
            fileWriter.write(System.lineSeparator());
        }
        fileReader.close();
        fileWriter.close();
        reader1.close();
    }
}
