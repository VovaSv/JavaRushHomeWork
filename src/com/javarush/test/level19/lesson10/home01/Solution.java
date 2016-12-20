package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
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
        Map<String, ArrayList<Double>> map = new HashMap<>();
        String line;
        while (reader.ready())
        {
            boolean exist = false;
            if ((line = reader.readLine()) != null)
            {
                final String[] arr = line.split(" ");
                if (map.size() > 0)
                {
                    for (Map.Entry<String, ArrayList<Double>> entry : map.entrySet())
                    {
                        if (entry.getKey().equals(arr[0]))
                        {
                            exist = true;
                        }
                    }
                    if (exist)
                    {
                        for (Map.Entry<String, ArrayList<Double>> entry : map.entrySet())
                        {
                            if (entry.getKey().equals(arr[0]))
                            {
                                entry.getValue().add(Double.valueOf(arr[1]));
                            }
                        }


                    } else
                    {
                        map.put(arr[0], new ArrayList<Double>()
                        {{
                                add(Double.valueOf(arr[1]));
                            }});

                    }
                } else
                {
                    map.put(arr[0], new ArrayList<Double>()
                    {{
                            add(Double.valueOf(arr[1]));
                        }});

                }

            }
        }
        reader.close();
        if(map.size() > 0 )
        {
            Set<String> set = new TreeSet<>();
            for (Map.Entry<String, ArrayList<Double>> entry : map.entrySet())
            {
                Double sum = 0.0;
                for (int i = 0; i < entry.getValue().size(); i++)
                {
                    sum += entry.getValue().get(i);
                }
                set.add(entry.getKey() + " " + sum);
            }
            for (String line1 : set){
                System.out.println(line1);
            }
        }

    }
}
