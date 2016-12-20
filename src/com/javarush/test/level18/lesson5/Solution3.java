package com.javarush.test.level18.lesson5;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution3 {
    public static void main(String[] args) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        FileInputStream inputStream1 = new FileInputStream(fileName1);
        FileOutputStream outputStream2 = new FileOutputStream(fileName2);
        FileOutputStream outputStream3 = new FileOutputStream(fileName3);
        int count = inputStream1.available();
        boolean isOdd = (inputStream1.available() % 2 == 0) ? false : true;
        if(isOdd){
            for (int i = 0; i < count/2 + 1; i++)
            {
                outputStream2.write(inputStream1.read());
            }
            for (int i = count/2 + 1; i < count; i++)
            {
                outputStream3.write(inputStream1.read());
            }
        }
        else {
            for (int i = 0; i < count/2; i++)
            {
                outputStream2.write(inputStream1.read());
            }
            for (int i = count/2; i < count; i++)
            {
                outputStream3.write(inputStream1.read());
            }

        }

        reader.close();
        inputStream1.close();
        outputStream2.close();
        outputStream3.close();

    }
}