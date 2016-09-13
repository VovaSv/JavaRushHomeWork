package com.javarush.test.Thread_Thinking_Java;

/**
 * Created by vladimis on 9/13/2016.
 */
public class MainThread
{
 public static class LiftOff implements Runnable {
     protected int countDown = 10;
     private static int taskCount = 0;
     private final int id = taskCount++;
     public LiftOff(){}
     public LiftOff(int countDown){
         this.countDown = countDown;
     }
     public String status(){
         return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
     }

     @Override
     public void run()
     {
      while (countDown-- > 0){
          System.out.println(status());
          Thread.yield();
      }
     }
 }

    public static void main(String[] args)
    {
        LiftOff launch = new LiftOff();
        launch.run();
    }

}
