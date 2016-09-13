package com.javarush.test.level05.lesson12.home03;

/* Создай классы Dog, Cat, Mouse
Создай классы Dog, Cat, Mouse. Добавь по три поля в каждый класс, на твой выбор. Создай объекты для героев мультика Том и Джерри. Так много, как только вспомнишь.
Пример:
Mouse jerryMouse = new Mouse(“Jerry”, 12 , 5), где 12 - высота в см, 5 - длина хвоста в см.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Mouse jerryMouse = new Mouse("Jerry", 12, 5);
        Mouse littleMouse = new Mouse("Little", 4, 1);
        Cat tomCat = new Cat("Tom", 30, 15);
        Cat cat1 = new Cat("Katy", 31, 12);
        Cat cat2 = new Cat("Koty", 21, 10);
        Dog dog = new Dog("Dog", 25, 10);

    }

    public static class Mouse
    {
        String name;
        int height;
        int tail;
        public static int c = 4;

        public Mouse(String name, int height, int tail)
        {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }
    }

    public static class Dog
    {
        String name;
        int tail;
        int height;

        public Dog(String name, int height, int tail)
        {
            this.name = name;
            this.height = height;
            this.tail = tail;
        }

    }

    public static class Cat
    {
        String name;
        int tail;
        int height;

        public Cat(String name, int tail, int height)
        {
            this.name = name;
            this.tail = tail;
            this.height = height;
        }

    }
}
