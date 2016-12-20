package com.javarush.test.level19.lesson10.home04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки. Не использовать try-with-resources

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution
{
    public static List<String> words = new ArrayList<String>();

    static
    {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader fileBuffReader = new BufferedReader(new FileReader(fileName));
        List<String> outputList = new ArrayList<>();
        while (fileBuffReader.ready())
        {
            String line = fileBuffReader.readLine();
            int count = 0;
            if (null != line)
            {
                List<String>  listOfWords = Arrays.asList(line.split(" "));
                for (String word : words){
                    if(listOfWords.contains(word))
                    count++;
                }
                if(count == 2)
                    outputList.add(line);
            }
        }
        for (String out : outputList){
            System.out.println(out);
        }
        fileBuffReader.close();
    }
}
