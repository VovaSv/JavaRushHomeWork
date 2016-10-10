package com.javarush.test.level17.lesson10.home04;

/* Синхронизированные методы
Установить модификатор synchronized только тем методам, которым необходимо.
Объект класса Solution будет использоваться нитями.
Метод 0. Не трогаем. Присваивает локальной переменной значение метода 3, который synchronized (см. далее)
Метод 1. при создании объекта Solution в методе пересчитается значение param, поэтому синхронизируем
Метод 2. Не трогаем. Лишь работает с аргументом метода, который даже не является ссылочным.
Метод 3. Ставим synchronized. Явно меняет поле нашего экземпляра класса.
Метод 4. меняет поле класса, которое внутри класса вызывается методом 6,
Метод 5. Не трогаем. Создает новый экземпляр StringBuffer(), с ним и работает, аргумент метода не меняет.
Метод 6. Ставим synchronized. Работает с выводом в консоль, меняет поле нашего экземпляра класса.
Метод 7. Не трогаем. С общими ресурсами не работает, ничего не меняет, никого не трогает.

*/

public class Solution {
    private double param = Math.random();
    private StringBuilder sb = new StringBuilder();

    private  void method0() {
        double i = method3();
    }

    protected synchronized void method1(String param1) {
        Solution solution = new Solution();
        solution.method0();
    }

    public  void method2(int param1) {
        param1++;
    }

    synchronized double method3() {
        double random = Math.random();
        param += 40.7;
        return random + param;
    }

    private synchronized void method4() {
        sb.append(1).append(1).append(1).append(1);
    }

    protected  void method5(String param2) {
        new StringBuffer().append(param2).append(param2).append(param2);
    }

    public synchronized String method6(int param2) {
        System.out.println("Thinking....");
        method7(5e-2);
        sb = new StringBuilder("Got it!.");
        return sb.toString();
    }

     String method7(double param2) {
        return "" + param2;
    }

}
