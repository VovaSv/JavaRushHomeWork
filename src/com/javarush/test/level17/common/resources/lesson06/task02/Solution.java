package com.javarush.test.level17.common.resources.lesson06.task02;

/* Предложения
Не используя synchronized сделайте так, чтобы количество сделанных и принятых предложений было одинаковым.
*/

public class Solution {
    //public  static  int proposal = 0;
    public volatile static  int proposal = 0;

    public static void main(String[] args) {
        new MakeProposal("MakeProposal").start();
        new AcceptProposal("AcceptProposal").start();
    }

    public static class MakeProposal extends Thread {

        public MakeProposal(String name)
        {
            super(name);
        }

        @Override
        public void run() {
            int thisProposal = proposal;

            while (proposal < 10) {
                System.out.println("Сделано предложени №" + (thisProposal + 1));
                proposal = ++thisProposal;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class AcceptProposal extends Thread {
        public AcceptProposal(String name)
        {
            super(name);
        }
        //Just adde my comments Added here me

        @Override
        public void run() {
            int thisProposal = proposal;

            while (thisProposal < 10) {
                if (thisProposal != proposal) {
                    System.out.println("Принято предложение №" + proposal);
                    thisProposal = proposal;
                }
            }
        }
    }
}