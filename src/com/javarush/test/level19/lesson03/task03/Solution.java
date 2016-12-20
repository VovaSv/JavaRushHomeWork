package com.javarush.test.level19.lesson03.task03;

/* Адаптация нескольких интерфейсов
Адаптировать IncomeData к Customer и Contact.
Классом-адаптером является IncomeDataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
Дополнить телефонный номер нулями до 10 цифр при необходимости (смотри примеры)
Обратите внимание на формат вывода фамилии и имени человека
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static Map<String, String> countries = new HashMap<String, String>(){{
        put("UA","Ukraine");
        put("RU","Russia");
        put("CA","Canada");

    }};

    public static void main(String[] args)
    {
        IncomeDataImpl incomeData = new IncomeDataImpl();
        Customer customer = new IncomeDataAdapter(incomeData);
        Contact contact= new IncomeDataAdapter(incomeData);
        System.out.println(customer.getCompanyName());
        System.out.println(customer.getCountryName());
        System.out.println(contact.getName());
        System.out.println(contact.getPhoneNumber());

    }

    public static class IncomeDataAdapter implements Customer,Contact{

        private IncomeData incomeData;
        IncomeDataAdapter(IncomeData incomeData){
            this.incomeData = incomeData;
        }
        @Override
        public String getName()
        {
            return incomeData.getContactLastName() + ", " + incomeData.getContactFirstName();
        }

        @Override
        public String getPhoneNumber()
        {
            String phoneNumber = String.format("%010d",incomeData.getPhoneNumber());
            return "+" + incomeData.getCountryPhoneCode() + "(" + phoneNumber.substring(0,3) + ")" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6,8) + "-" + phoneNumber.substring(8,10);
        }

        @Override
        public String getCompanyName()
        {
            return incomeData.getCompany();
        }

        @Override
        public String getCountryName()
        {
            return countries.get(incomeData.getCountryCode());
        }
    }

    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }


    public static class IncomeDataImpl implements IncomeData {

        @Override
        public String getCountryCode()
        {
            return "UA";
        }

        @Override
        public String getCompany()
        {
            return "JavaRush Ltd.";
        }

        @Override
        public String getContactFirstName()
        {
            return "Ivan";
        }

        @Override
        public String getContactLastName()
        {
            return "Ivanov";
        }

        @Override
        public int getCountryPhoneCode()
        {
            return 38;
        }

        @Override
        public int getPhoneNumber()
        {
            return 501234567;
        }
    }
}