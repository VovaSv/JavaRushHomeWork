package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{

        String fileName1 = args[0];
        String fileName2 = args[1];

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        FileWriter writer = new FileWriter(fileName2);
        while (reader.ready()){
            String[] arr = reader.readLine().split(" ");
            for (int i = 0; i < arr.length; i++)
            {
                if(arr[i].matches(".*\\d+.*"))
                writer.write(arr[i] + " ");
            }
        }
        reader.close();
        writer.close();
    }
}
