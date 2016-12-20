package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        File file1 = new File(fileName1);
        FileInputStream input1 = new FileInputStream(file1);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(input1));
        String line = "";
        String result1 = "";
        String result2 = "";
        while ( (line = reader1.readLine()) != null){
            result1 = result1 + line + '\n';
        }


        file1.delete();
        FileInputStream input2 = new FileInputStream(fileName2);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(input2));
        while ( (line = reader2.readLine()) != null){
            result2 = result2 + line + '\n';
        }

        FileOutputStream outputStream = new FileOutputStream(file1);
        outputStream.write(result2.getBytes());
        outputStream.write(result1.getBytes());
        reader.close();
        reader1.close();
        reader2.close();
        input1.close();
        input2.close();
        outputStream.close();
    }
}
