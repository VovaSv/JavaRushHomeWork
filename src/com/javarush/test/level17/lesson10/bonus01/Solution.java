package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution
{
    public static List<Person> allPeople = new ArrayList<Person>();
    public static String[] paramArr;
    public static String pattern = "dd/MM/yyyy";

    static
    {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException
    {
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
        if ("м".equals(paramArr[2]))
            allPeople.add(Person.createMale(paramArr[1], new SimpleDateFormat(pattern,Locale.ENGLISH).parse(paramArr[3])));
        else
            allPeople.add(Person.createFemale(paramArr[1], new SimpleDateFormat(pattern,Locale.ENGLISH).parse(paramArr[3])));
        System.out.println((allPeople.size() - 1));
    }

    public static void updated() throws ParseException
    {
      if(null != allPeople.get(Integer.parseInt(paramArr[1])).getName())
      {
          allPeople.get(Integer.parseInt(paramArr[1])).setName(paramArr[2]);
          if ("м".equals(paramArr[3]))
              allPeople.get(Integer.parseInt(paramArr[1])).setSex(Sex.MALE);
          else
              allPeople.get(Integer.parseInt(paramArr[1])).setSex(Sex.FEMALE);
          allPeople.get(Integer.parseInt(paramArr[1])).setBirthDay(new SimpleDateFormat(pattern, Locale.ENGLISH).parse(paramArr[4]));
      }

    }

    public static void delete()
    {
        allPeople.get(Integer.parseInt(paramArr[1])).setSex(null);
        allPeople.get(Integer.parseInt(paramArr[1])).setBirthDay(null);
        allPeople.get(Integer.parseInt(paramArr[1])).setName(null);
    }

    public static void info()
    {
            System.out.println(allPeople.get(Integer.parseInt(paramArr[1])).getName() + " " + ((allPeople.get(Integer.parseInt(paramArr[1])).getSex()).toString().equals("MALE")?"м":"ж") +" " + (new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH).format(allPeople.get(Integer.parseInt(paramArr[1])).getBirthDay())));

    }
}
