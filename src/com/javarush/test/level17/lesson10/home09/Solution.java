package com.javarush.test.level17.lesson10.home09;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines
4. Если список allLines НЕ содержит каких-либо строк, которые есть в forRemoveLines, то
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();
    public static String fileName1;
    public static String fileName2;
    public static String FILE_NAME = "c:\\New folder\\vova.txt";
    public static String FILE_NAME2 = "c:\\New folder\\vova2.txt";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();

            BufferedReader fileReaderAll = new BufferedReader(new FileReader(fileName1));
            BufferedReader fileReaderFor = new BufferedReader(new FileReader(fileName2));
            String str;
            while ((str = fileReaderAll.readLine())!=null){
                allLines.add(str);
            }
            while ((str = fileReaderFor.readLine())!=null){
                forRemoveLines.add(str);
            }

            try
            {
               new Solution().joinData();
            }
            catch ( CorruptedDataException e){

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public  void joinData () throws CorruptedDataException {
        boolean foundEqual = false;
        boolean cleanAll = false;
        for (String s: forRemoveLines){
            for (String t : allLines){
                if(s.equals(t))
                    foundEqual = true;
            }
            if(!foundEqual){
                cleanAll  = true;
            }
            foundEqual = false;
        }

        if(cleanAll){
            allLines.clear();
            System.out.println(allLines);
            throw new CorruptedDataException();
        }
        else {
            for (String s: forRemoveLines){
                allLines.remove(s);
            }
            System.out.println(allLines);

        }

    }
}
