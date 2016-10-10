package com.javarush.test.level17.lesson10.bonus02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD 2
CrUD Batch - multiple Creation, Updates, Deletion
!!!РЕКОМЕНДУЕТСЯ выполнить level17.lesson10.bonus01 перед этой задачей!!!

Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ...
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ...
-d id1 id2 id3 id4 ...
-i id1 id2 id3 id4 ...
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-с  - добавляет всех людей с заданными параметрами в конец allPeople, выводит id (index) на экран в соответствующем порядке
-u  - обновляет соответствующие данные людей с заданными id
-d  - производит логическое удаление всех людей с заданными id
-i  - выводит на экран информацию о всех людях с заданными id: name sex bd

id соответствует индексу в списке
Формат вывода даты рождения 15-Apr-1990
Все люди должны храниться в allPeople
Порядок вывода данных соответствует вводу данных
Обеспечить корректную работу с данными для множества нитей (чтоб не было затирания данных)
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    public static String[] paramArr;
    public static String pattern = "dd/MM/yyyy";
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        paramArr = args;
        switch (args[0])
        {
            case "-c":
                addToEnd();
                break;
            case "-u":
                updated();
                break;
            case "-d":
                delete();
                break;
            case "-i":
                info();
                break;
            default:
                System.out.println("No such method");
        }
    }

    public static void addToEnd() throws ParseException
    {
        synchronized (allPeople)
        {
            for (int i = 1; i < paramArr.length; i += 3)
            {
                if ("м".equals(paramArr[(i + 1)]))
                    allPeople.add(Person.createMale(paramArr[i], new SimpleDateFormat(pattern, Locale.ENGLISH).parse(paramArr[(i + 2)])));
                else
                    allPeople.add(Person.createFemale(paramArr[i], new SimpleDateFormat(pattern, Locale.ENGLISH).parse(paramArr[(i + 2)])));
                System.out.println((allPeople.size() - 1));
            }
        }
    }

    public static void updated() throws ParseException
    {
        synchronized (allPeople)
        {
            for (int i = 1; i < paramArr.length; i += 4)
            {
                if (null != allPeople.get(Integer.parseInt(paramArr[i])).getName())
                {
                    allPeople.get(Integer.parseInt(paramArr[i])).setName(paramArr[i + 1]);
                    if ("м".equals(paramArr[i + 2]))
                        allPeople.get(Integer.parseInt(paramArr[i])).setSex(Sex.MALE);
                    else
                        allPeople.get(Integer.parseInt(paramArr[i])).setSex(Sex.FEMALE);
                    allPeople.get(Integer.parseInt(paramArr[i])).setBirthDay(new SimpleDateFormat(pattern, Locale.ENGLISH).parse(paramArr[i + 3]));
                }
            }
        }
    }

    public static void delete()
    {
        synchronized (allPeople)
        {
            for (int i = 1; i < paramArr.length; i++)
            {
                allPeople.get(Integer.parseInt(paramArr[i])).setSex(null);
                allPeople.get(Integer.parseInt(paramArr[i])).setBirthDay(null);
                allPeople.get(Integer.parseInt(paramArr[i])).setName(null);

            }
        }
    }

    public static void info()
    {
        synchronized (allPeople)
        {
            for (int i = 1; i < paramArr.length; i++)
            {
                System.out.println(allPeople.get(Integer.parseInt(paramArr[i])).getName() + " " + ((allPeople.get(Integer.parseInt(paramArr[i])).getSex()).toString().equals("MALE") ? "м" : "ж") + " " + (new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(allPeople.get(Integer.parseInt(paramArr[i])).getBirthDay())));
            }
        }
    }
}
