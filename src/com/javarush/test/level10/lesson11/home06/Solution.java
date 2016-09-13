package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

import java.util.Date;

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        public String name;
        public int age;
        public String sex;
        public String sureName;
        public Date birhtday;
        public int length;

        public Human(String name){
            this.name = name;
        }
        public Human(Human human){
            this.name = human.name;
            this.sureName = human.sureName;

        }
        public Human(int age){
            this.age = age;
        }
        public Human(String sureName, String name){
            this.sureName = sureName;
            this.name = name;
        }
        public Human(Date date){
            this.birhtday = date;
        }
        public Human(int length, int age){
            this.length = length;
            this.age = age;
        }
        public Human(String sureName, int age){
            this.sureName = sureName;
            this.age = age;
        }
        public Human(String name, String sureName, int age){
            this.sureName = sureName;
            this.name = name;
            this.age = age;
        }
        public Human(Date date, String name){
            this.birhtday = date;
            this.name = name;
        }
        public Human(){

        }

    }
}
