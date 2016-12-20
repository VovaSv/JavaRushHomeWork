package com.javarush.test.level19.lesson03.task04;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные одного человека.
*/

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(new File("c://tmp//input.txt"));
        PersonScanner personScanner =  new PersonScannerAdapter(scanner);
        System.out.println(personScanner.read());
        personScanner.close();
    }


    public static class PersonScannerAdapter implements PersonScanner{

        private Scanner scanner;
        PersonScannerAdapter(Scanner scanner){
            this.scanner = scanner;
        }

        @Override
        public Person read() throws IOException
        {
            Person personObj = null;
            Date date = null;
            String[] personArr = null;
            Calendar calendar = null;
            if(scanner.hasNext()){
                String person = scanner.nextLine();
                personArr = person.split(" ");
                SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
                calendar = new GregorianCalendar(Integer.valueOf(personArr[5]),Integer.valueOf(personArr[4]),Integer.valueOf(personArr[3]));
/*                try
                {
                    date = sdf.parse(personArr[3] + " " + personArr[4] +" "+ personArr[5]);
                }
                catch (ParseException e){

                }*/
            }


            personObj = new Person(personArr[1],personArr[2],personArr[0],calendar.getTime());
            return personObj;
        }

        @Override
        public void close() throws IOException
        {
            scanner.close();
        }
    }
}
