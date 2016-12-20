package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        Map<String, Double> map = new HashMap<>();
        String line;
        while (reader.ready())
        {
            if ((line = reader.readLine()) != null)
            {
                final String[] arr = line.split(" ");
                if (map.size() > 0)
                {
                    if (map.containsKey(arr[0]))
                    {
                        map.put(arr[0], (map.get(arr[0]) + Double.valueOf(arr[1])));

                    } else
                    {
                        map.put(arr[0], new Double(arr[1]));
                    }
                } else
                {
                    map.put(arr[0], new Double(arr[1]));
                }

            }
        }
        reader.close();
        if (map.size() > 0)
        {
            List<Double> values = Arrays.asList(map.values().toArray(new Double[0]));
            Collections.sort(values);
            for (Map.Entry<String, Double> entry : map.entrySet())
            {
                if (entry.getValue() == values.get(values.size() - 1))
                {
                    System.out.println(entry.getKey());
                }
            }
        }

    }
}
