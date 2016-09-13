package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args)
    {
        Human Sin1 = new Human("Sin1",true,2, new ArrayList<Human>());
        Human Sin2 = new Human("Sin2",true,3, new ArrayList<Human>());
        Human Doch1 = new Human("Doch1",false,4, new ArrayList<Human>());
        ArrayList<Human> child3 = new ArrayList<>();
        child3.add(Sin1);
        child3.add(Sin2);
        child3.add(Doch1);

        Human Papa = new Human("Papa",true,41, child3);
        Human Mama = new Human("Mama",false,34, child3);

        ArrayList<Human> child1 = new ArrayList<>();
        child1.add(Papa);
        Human Ded1 = new Human("Ded1",true,67,child1);
        Human Baba1 = new Human("Baba1",false,51, child1);

        ArrayList<Human> child2 = new ArrayList<>();
        child2.add(Mama);
        Human Ded2 = new Human("Ded2",true,55, child2);
        Human Baba2 = new Human("Baba2",false,52, child2);

        System.out.println(Sin1);
        System.out.println(Sin2);
        System.out.println(Doch1);
        System.out.println(Papa);
        System.out.println(Mama);
        System.out.println(Ded1);
        System.out.println(Baba1);
        System.out.println(Ded2);
        System.out.println(Baba2);
    }

    public static class Human
    {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }


        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
