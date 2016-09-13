package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
Подсказка: Нить 4 можно проверить методом isAlive()
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static {
        threads.add(new MyThread1());
        threads.add(new MyThread2());
        threads.add(new MyThread3());
        threads.add(new MyThread4());
        threads.add(new MyThread5());
    }

    public static void main(String[] args)
    {
        for (Thread tr : threads){
                tr.start();
        }

        Thread t1 = threads.get(0);
        Thread t2 = threads.get(1);
        Thread t3 = threads.get(2);
        MyThread4 t4 = (MyThread4)threads.get(3);
        t4.showWarning();

        Thread t5 = threads.get(4);
    }


    public static class MyThread1 extends Thread{
        @Override
        public void run()
        {
            while (true){

            }
        }
    }
    public static class MyThread2 extends Thread{
        @Override
        public void run()
        {
            try
            {
                while (true){
                Thread.sleep(1);
                }
            }
            catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }
    public static class MyThread3 extends Thread{
        @Override
        public void run()
        {
            while (true){
                try
                {
                    System.out.println("Ура");
                    Thread.sleep(500);

                }catch (InterruptedException e){

                }

            }
        }
    }
    public static class MyThread4 extends Thread implements Message{

        @Override
        public void showWarning()
        {
            interrupt();
            try
            {
                this.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            while (!isInterrupted()){

            }

        }
    }
    public static class MyThread5 extends Thread{

        public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        public String str;
        public int sum;

        @Override
        public void run()
        {
            try
            {
                while(!"N".equals(str = reader.readLine())){
                    sum+=Integer.valueOf(str);

                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            System.out.println(sum);
        }
    }
}
