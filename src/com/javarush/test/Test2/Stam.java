package com.javarush.test.Test2;

/**
 * Created by vladimis on 7/27/2016.
 */
public class Stam
{
    static class Printer implements Runnable
    {
        private String name;
        public Printer(String name)
        {
            this.name = name;
        }
        public void run()
        {
            System.out.println("I’m " + this.name);
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        Printer printer1 = new Printer("Коля");
        Thread thread1 = new Thread(printer1);
        thread1.start();

        //thread1.join();

        System.out.println("Finished Main");
    }

}
