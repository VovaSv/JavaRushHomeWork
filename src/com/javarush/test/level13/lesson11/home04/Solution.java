package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            String outputFileName = reader.readLine();
            OutputStream outputStream = new FileOutputStream(outputFileName);
            String line;
            while (!(line = reader.readLine()).equals("exit"))
            {
                outputStream.write(line.getBytes());
                outputStream.write('\n');
            }
            outputStream.write(line.getBytes());
        }
        catch (IOException e)
        {

        }
    }
}
