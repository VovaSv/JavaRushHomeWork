package com.javarush.test.Test2;
/* А без interrupt слабо?
Разберись, как работает программа.
Сделай так, чтобы в методе ourInterruptMethod можно было сделать так, чтобы нить TestThread завершилась сама.
Нельзя использовать метод interrupt.
*/

public class Solution16104 {
    public static volatile boolean isRun = true;
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new TestThread());
        t.start();
        Thread.sleep(3000);
        ourInterruptMethod();
    }

    public static void ourInterruptMethod() {
        isRun = false;


    }

    public static class TestThread implements Runnable {


        public void run() {
            while(isRun) {

                try {
                    System.out.println("he-he");
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                }
            }
        }
    }
}
