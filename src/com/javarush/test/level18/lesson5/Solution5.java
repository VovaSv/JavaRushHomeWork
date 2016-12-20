package com.javarush.test.level18.lesson5;

/* DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки
2.2 выбросить исключение DownloadException
*/

import java.io.*;

public class Solution5 {
    public static void main(String[] args) throws DownloadException,IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String fileName = reader.readLine();
            FileInputStream fileInput = new FileInputStream(fileName);
            FileOutputStream fileInput1 = new FileOutputStream(fileName);
            int count = fileInput.available();
            if(count < 1000)
            {
                reader.close();
                fileInput.close();
                throw new DownloadException();
            }
        }
    }

    public static class DownloadException extends Exception{

    }
}
