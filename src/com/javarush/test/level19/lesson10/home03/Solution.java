package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args){
        BufferedReader reader = null;
        SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
        Date date1 = null;
        String name;
        try
        {
             reader = new BufferedReader(new FileReader(args[0]));
            while (reader.ready())
            {
                String line = reader.readLine();
                if (null != line)
                {
                    name = (line.replaceAll("[0-9]", "")).trim();
                    String date = (line.replaceAll("[^0-9]", " ")).trim();
                    if (null != date)
                    {
                        try
                        {
                            date1 = format.parse(date);

                        }
                        catch (ParseException e)
                        {

                        }
                    }
                    PEOPLE.add(new Person(name, date1));
                }
            }
        }catch (IOException e){

        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e){

            }
        }


    }

}
