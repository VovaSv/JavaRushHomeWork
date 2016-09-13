package com.javarush.test.level13.lesson11.home03;

/* Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String fileName = reader.readLine();
            InputStream inputStream = new FileInputStream(fileName);
            while ( inputStream.available() > 0){
               int unit = inputStream.read();
                System.out.print((char)unit);
            }
            inputStream.close();
            reader.close();
        }
        catch (IOException e){
        }
    }
}
