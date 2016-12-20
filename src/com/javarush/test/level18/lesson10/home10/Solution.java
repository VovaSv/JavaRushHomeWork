package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer,String> tmpMap = new HashMap<>();
        String fileName = "";
        String fullFileName = "";
        String dir = "c:\\tmp\\";
        while (!"end".equals(fileName = reader.readLine())){
            String arr[] = fileName.split(".part");
            fullFileName = arr[0];
            tmpMap.put(Integer.valueOf(arr[arr.length-1]),fileName);
        }
        reader.close();
        File file = new File(dir,fullFileName);
        ArrayList list = new ArrayList(tmpMap.keySet());
        Collections.sort(list);
        FileOutputStream output = new FileOutputStream(file);
        for (int i = 0; i < list.size(); i++)
        {
            FileInputStream fileInputStream = new FileInputStream(dir + tmpMap.get(list.get(i)));
            byte[] buff = new byte[fileInputStream.available()];
            fileInputStream.read(buff);
            output.write(buff);
            output.write(System.lineSeparator().getBytes());
            fileInputStream.close();

        }
        output.close();

    }
}
