package com.javarush.test.level19.lesson03.task05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/* Закрепляем адаптер
Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
*/

public class Solution {
    private static Map<String,String> countries = new HashMap<String,String>(){{
        put("UA","Ukraine");
        put("RU","Russia");
        put("CA","Canada");

    }};


    public static class DataAdapter implements RowItem {

        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.contact = contact;
            this.customer = customer;
        }

        @Override
        public String getCountryCode()
        {
            for (Map.Entry<String,String> entry : countries.entrySet()){
                if(customer.getCountryName().equals(entry.getValue()))
                    return entry.getKey();
            }
            return null;
        }

        @Override
        public String getCompany()
        {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName()
        {
            return (contact.getName().split(","))[1].trim();
        }

        @Override
        public String getContactLastName()
        {
            return (contact.getName().split(","))[0].trim();
        }

        @Override
        public String getDialString()
        {
            try
            {
                new FileReader("");
            }
            catch (FileNotFoundException e){

            }

            return "callto://" + (contact.getPhoneNumber().replaceAll("[()-]", ""));
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }

}