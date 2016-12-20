package com.javarush.test.level18.lesson5;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution4 {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        List<Integer> list = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName2);


        while (fileInputStream.available() > 0){
            list.add(fileInputStream.read());
        }
        Collections.reverse(list);
        for (int num : list){
            fileOutputStream.write(num);
        }

        reader.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}

