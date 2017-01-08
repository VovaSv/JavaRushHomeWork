package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution
{
    private static String attribute;

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        attribute = args[0];
        BufferedReader readerFile = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        while (readerFile.ready())
        {
            String line = readerFile.readLine();
            count++;
            if (!line.startsWith("<") && count > 1)
            {
                stringBuilder.append(line + " ");
            } else
            {
                stringBuilder.append(line);
            }
        }
        readerFile.close();
        String stringToSearch = stringBuilder.toString().replaceAll("\r\n", "");
        parseHTML(stringToSearch);
    }

    public static void parseHTML(String stringToSearch)
    {
        Pattern pattern = Pattern.compile("<\\/?" + attribute + "( [^>]*|>)");
        Matcher matcher = pattern.matcher(stringToSearch);
        Stack<Integer> stack = new Stack<>();
        int start, end, index;
        while (matcher.find())
        {
            start = matcher.start();
            end = matcher.end();
            if (!stringToSearch.substring(start).startsWith("</" + attribute))
            {
                stack.add(start);
            } else
            {
                index = stack.pop();
                if (stack.size() == 0)
                {
                    System.out.println(stringToSearch.substring(index, end));
                    parseHTML(stringToSearch.substring(index + 1 + attribute.length(), start));
                }
            }
        }
    }
}
