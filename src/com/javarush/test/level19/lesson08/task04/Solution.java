package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream currentSOut = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream newPrintStream = new PrintStream(byteArrayOutputStream);
        System.setOut(newPrintStream);
        testString.printSomething();
        System.setOut(currentSOut);
        String line = byteArrayOutputStream.toString().replaceAll("[\\n|\\r]","");
        String [] arr = line.split(" ");
        int a = Integer.valueOf(arr[0]);
        int b = Integer.valueOf(arr[2]);
        String sign = arr[1];
        int result = 0;
        switch (sign){
            case "+": result = Add(a,b);
                break;
            case "-": result = Minus(a,b);
                break;
            case "*": result = Multi(a,b);
        }

        System.out.println(line + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
    public static int Add(int a, int b) {
        return a+b;
    }
    public static int Minus(int a, int b) {
        return a-b;
    }
    public static int Multi(int a, int b) {
        return a*b;
    }


}

